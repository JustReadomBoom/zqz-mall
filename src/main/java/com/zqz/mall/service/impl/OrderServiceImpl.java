package com.zqz.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqz.mall.common.bean.*;
import com.zqz.mall.constants.Constants;
import com.zqz.mall.dao.*;
import com.zqz.mall.entity.*;
import com.zqz.mall.enums.OrderStatusEnum;
import com.zqz.mall.enums.PayTypeEnum;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.exception.MallException;
import com.zqz.mall.service.OrderService;
import com.zqz.mall.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: OrderServiceImpl
 * @Date: Created in 9:20 2023-6-30
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private ShoppingCartItemMapper shoppingCartItemMapper;
    @Resource
    private MallUserAddressMapper userAddressMapper;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    private OrderAddressMapper orderAddressMapper;
    @Autowired
    private PlatformTransactionManager txManager;


    @Override
    public PageResult<OrderContentVo> getListPage(Integer pageNumber, Integer status, Long userId) {
        PageResult<OrderContentVo> result = new PageResult<>();
        //默认每页5条
        Page<Object> page = PageHelper.startPage(pageNumber, 5);
        OrderListPageParam pageParam = new OrderListPageParam();
        pageParam.setUserId(userId);
        pageParam.setOrderStatus(Optional.ofNullable(status).map(Integer::byteValue).orElse(null));
        List<Order> orders = orderMapper.getListPage(pageParam);
        if (CollectionUtil.isEmpty(orders)) {
            throw new MallException(ResultEnum.DATA_NOT_EXIST.getResult());
        }
        //先查询订单项
        List<Long> orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());
        List<OrderItem> orderItemList = orderItemMapper.selectByOrderIds(orderIds);
        if (CollectionUtil.isEmpty(orderItemList)) {
            throw new MallException(ResultEnum.ORDER_ITEM_NOT_EXIST_ERROR.getResult());
        }
        Map<Long, List<OrderItem>> orderItemMap = orderItemList.stream().collect(groupingBy(OrderItem::getOrderId));

        //处理订单数据
        List<OrderContentVo> orderVoList = new ArrayList<>();
        orders.forEach(o -> {
            OrderContentVo orderVo = new OrderContentVo();
            BeanUtil.copyProperties(o, orderVo);
            orderVo.setOrderId(o.getId());
            orderVo.setOrderStatusString(OrderStatusEnum.getByStatus(o.getOrderStatus()).getName());
            if (orderItemMap.containsKey(orderVo.getOrderId())) {
                List<OrderItem> orderItems = orderItemMap.get(orderVo.getOrderId());
                List<OrderItemVo> itemVoList = BeanUtil.copyToList(orderItems, OrderItemVo.class);
                orderVo.setNewBeeMallOrderItemVOS(itemVoList);
            }
            orderVoList.add(orderVo);
        });
        result.setList(orderVoList);
        result.setCurrPage(pageNumber);
        result.setPageSize(5);
        result.setTotalCount((int) page.getTotal());
        result.setTotalPage(page.getPages());
        return result;
    }


    @Override
    public String createOrder(CreateOrderReq req, Long userId) {
        List<ShoppingCartContentVo> shoppingCartList = shoppingCartService.getShoppingCartItem(Arrays.asList(req.getCartItemIds()), userId);
        if (CollectionUtil.isEmpty(shoppingCartList)) {
            throw new MallException(ResultEnum.SHOPPING_ITEM_ERROR.getResult());
        }
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ShoppingCartContentVo cartContentVo : shoppingCartList) {
            BigDecimal sumPrice = cartContentVo.getSellingPrice().multiply(new BigDecimal(cartContentVo.getGoodsCount())).setScale(2, BigDecimal.ROUND_HALF_UP);
            totalPrice = totalPrice.add(sumPrice);
        }
        MallUserAddress address = userAddressMapper.selectByPrimaryKey(req.getAddressId());
        if (ObjectUtil.isEmpty(address)) {
            throw new MallException(ResultEnum.ADDRESS_NO_EXIST.getResult());
        }
        if (!address.getUserId().equals(userId)) {
            throw new MallException(ResultEnum.REQUEST_FORBIDEN_ERROR.getResult());
        }
        //保存订单
        List<Long> cartItemIds = shoppingCartList.stream().map(ShoppingCartContentVo::getCartItemId).collect(Collectors.toList());
        List<Long> goodsIds = shoppingCartList.stream().map(ShoppingCartContentVo::getGoodsId).collect(Collectors.toList());
        List<GoodsInfo> goodsInfos = goodsInfoMapper.selectByGoodIds(goodsIds);
        if (CollectionUtil.isEmpty(goodsInfos)) {
            throw new MallException(ResultEnum.GOODS_NOT_EXIST.getResult());
        }
        //检查是否包含已下架商品
        List<GoodsInfo> takeDownGoods = goodsInfos.stream().filter(g -> g.getGoodsSellStatus() != 0).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(takeDownGoods)) {
            throw new MallException(String.format(ResultEnum.SOME_GOODS_PUT_DOWN.getResult(), takeDownGoods.get(0).getGoodsName()));
        }
        Map<Long, GoodsInfo> goodsMap = goodsInfos.stream().collect(Collectors.toMap(GoodsInfo::getId, Function.identity(), (entity1, entity2) -> entity1));
        //判断商品库存
        shoppingCartList.forEach(s -> {
            //查出的商品中不存在购物车中的这条关联商品数据，直接返回错误提醒
            if (!goodsMap.containsKey(s.getGoodsId())) {
                throw new MallException(ResultEnum.SHOPPING_ITEM_ERROR.getResult());
            }
            //存在数量大于库存的情况，直接返回错误提醒
            if (s.getGoodsCount() > goodsMap.get(s.getGoodsId()).getStockNum()) {
                throw new MallException(ResultEnum.SHOPPING_ITEM_COUNT_ERROR.getResult());
            }
        });
        if (CollectionUtil.isNotEmpty(cartItemIds) && CollectionUtil.isNotEmpty(goodsIds) && CollectionUtil.isNotEmpty(goodsInfos)) {
            //手动提交事务
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = txManager.getTransaction(def);
            boolean commit = true;
            try {
                //删除购物项
                int d = shoppingCartItemMapper.deleteBatch(cartItemIds);
                if (d > 0) {
                    List<StockNumVo> stockNumVos = BeanUtil.copyToList(shoppingCartList, StockNumVo.class);
                    int u = goodsInfoMapper.updateStockNum(stockNumVos);
                    if (u < 1) {
                        throw new MallException(ResultEnum.SHOPPING_ITEM_COUNT_ERROR.getResult());
                    }
                    //生成订单号
                    String orderNo = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN) + RandomUtil.randomNumbers(6);
                    Order order = new Order();
                    order.setOrderNo(orderNo);
                    order.setUserId(userId);
                    //计算订单价格
                    BigDecimal priceTotal = BigDecimal.ZERO;
                    for (ShoppingCartContentVo cart : shoppingCartList) {
                        priceTotal = priceTotal.add(new BigDecimal(cart.getGoodsCount()).multiply(cart.getSellingPrice()));
                    }
                    order.setTotalPrice(priceTotal);
                    order.setExtraInfo(Constants.EMPTY_STR);
                    //生成订单项并保存订单项纪录
                    int i = orderMapper.insertSelective(order);
                    if (i < 1) {
                        throw new MallException(ResultEnum.SAVE_ORDER_FAIL.getResult());
                    }
                    Long orderId = orderMapper.selectIdByOrderNo(orderNo);
                    if (ObjectUtil.isEmpty(orderId)) {
                        throw new MallException(ResultEnum.ORDER_NOT_EXIST_ERROR.getResult());
                    }
                    //生成订单收货地址快照
                    OrderAddress orderAddress = new OrderAddress();
                    BeanUtil.copyProperties(address, orderAddress);
                    //重新置为空，避免新增失败
                    orderAddress.setId(null);
                    orderAddress.setOrderId(orderId);
                    //生成所有的订单项快照
                    List<OrderItem> newOrderItems = new ArrayList<>();
                    shoppingCartList.forEach(scl -> {
                        OrderItem orderItem = new OrderItem();
                        BeanUtil.copyProperties(scl, orderItem);
                        orderItem.setOrderId(orderId);
                        newOrderItems.add(orderItem);
                    });
                    int i2 = orderItemMapper.insertBatch(newOrderItems);
                    int i3 = orderAddressMapper.insertSelective(orderAddress);
                    if (i2 > 0 && i3 > 0) {
                        return orderNo;
                    }
                } else {
                    throw new MallException(ResultEnum.DELETE_CART_ITEM_FAIL.getResult());
                }
            } catch (Exception e) {
                log.error("创建订单过程中出现异常:msg=[{}]", e.getMessage(), e);
                commit = false;
            } finally {
                if (commit) {
                    txManager.commit(status);
                } else {
                    txManager.rollback(status);
                }
            }
        } else {
            throw new MallException(ResultEnum.OPERATE_ERROR.getResult());
        }
        return null;
    }

    @Override
    public GetOrderDetailResp getOrderDetail(String orderNo, Long userId) {
        GetOrderDetailResp resp = new GetOrderDetailResp();
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (ObjectUtil.isEmpty(order)) {
            throw new MallException(ResultEnum.DATA_NOT_EXIST.getResult());
        }
        if (!userId.equals(order.getUserId())) {
            throw new MallException(ResultEnum.REQUEST_FORBIDEN_ERROR.getResult());
        }
        List<OrderItem> orderItems = orderItemMapper.selectByOrderId(order.getId());
        if (CollectionUtil.isEmpty(orderItems)) {
            throw new MallException(ResultEnum.ORDER_ITEM_NOT_EXIST_ERROR.getResult());
        }

        List<OrderItemVo> itemVoList = BeanUtil.copyToList(orderItems, OrderItemVo.class);
        BeanUtil.copyProperties(order, resp);
        resp.setOrderStatusString(OrderStatusEnum.getByStatus(order.getOrderStatus()).getName());
        resp.setPayTypeString(PayTypeEnum.getPayByType(order.getPayType()).getName());
        resp.setNewBeeMallOrderItemVOS(itemVoList);
        return resp;
    }
}
