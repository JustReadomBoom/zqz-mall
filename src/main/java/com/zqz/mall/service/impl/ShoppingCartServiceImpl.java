package com.zqz.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqz.mall.common.bean.AddShoppingCartReq;
import com.zqz.mall.common.bean.GetShoppingCartListResp;
import com.zqz.mall.common.bean.PageResult;
import com.zqz.mall.common.bean.ShoppingCartContentVo;
import com.zqz.mall.dao.GoodsInfoMapper;
import com.zqz.mall.dao.ShoppingCartItemMapper;
import com.zqz.mall.entity.GoodsInfo;
import com.zqz.mall.entity.ShoppingCartItem;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.exception.MallException;
import com.zqz.mall.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: ShoppingCartServiceImpl
 * @Date: Created in 10:37 2023-6-26
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Resource
    private ShoppingCartItemMapper cartItemMapper;
    @Resource
    private GoodsInfoMapper goodsInfoMapper;


    @Override
    public GetShoppingCartListResp getList(Long userId) {
        List<ShoppingCartItem> cartItems = cartItemMapper.selectByUserId(userId, 20);
        if (CollectionUtil.isEmpty(cartItems)) {
            throw new MallException(ResultEnum.DATA_NOT_EXIST.getResult());
        }
        GetShoppingCartListResp listResp = parseData(cartItems);
        if (ObjectUtil.isEmpty(listResp)) {
            throw new MallException(ResultEnum.OPERATE_ERROR.getResult());
        }
        return listResp;
    }

    @Override
    public void addGoods(AddShoppingCartReq req, Long userId) {
        //小于单个商品的最小数量
        if (req.getGoodsCount() < 1) {
            throw new MallException(ResultEnum.SHOPPING_CART_ITEM_NUMBER_ERROR.getResult());
        }
        //超出单个商品的最大数量
        if (req.getGoodsCount() > 5) {
            throw new MallException(ResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult());
        }
        Long goodsId = req.getGoodsId();
        ShoppingCartItem cartItem = cartItemMapper.selectByUserIdAndGoodsId(userId, goodsId);
        if (ObjectUtil.isEmpty(cartItem)) {
            throw new MallException(ResultEnum.SHOPPING_CART_ITEM_EXIST_ERROR.getResult());
        }
        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsId);
        if (ObjectUtil.isEmpty(goodsInfo)) {
            throw new MallException(ResultEnum.GOODS_NOT_EXIST.getResult());
        }
        //超出最大数量
        int count = cartItemMapper.selectCountByUserId(userId);
        if (count > 20) {
            throw new MallException(ResultEnum.SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR.getResult());
        }
        ShoppingCartItem item = new ShoppingCartItem();
        BeanUtil.copyProperties(req, item);
        item.setUserId(userId);
        int i = cartItemMapper.insertSelective(item);
        if (i < 1) {
            throw new MallException(ResultEnum.OPERATE_ERROR.getResult());
        }
    }

    @Override
    public PageResult<ShoppingCartContentVo> getListPage(Integer pageNumber, Long userId) {
        PageResult<ShoppingCartContentVo> result = new PageResult<>();
        //默认每页5条
        Page<Object> page = PageHelper.startPage(pageNumber, 5);
        List<ShoppingCartItem> cartItems = cartItemMapper.selectByUserIdPage(userId);
        if (CollectionUtil.isEmpty(cartItems)) {
            throw new MallException(ResultEnum.DATA_NOT_EXIST.getResult());
        }
        GetShoppingCartListResp parseData = parseData(cartItems);
        List<ShoppingCartContentVo> dataList = Optional.ofNullable(parseData).map(GetShoppingCartListResp::getList).orElseThrow(() -> new MallException(ResultEnum.DATA_PARSE_FAIL.getResult()));
        result.setList(dataList);
        result.setCurrPage(pageNumber);
        result.setPageSize(5);
        result.setTotalCount((int) page.getTotal());
        result.setTotalPage(page.getPages());
        return result;

    }

    @Override
    public List<ShoppingCartContentVo> getShoppingCartItem(List<Long> itemIds, Long userId) {
        List<ShoppingCartItem> cartItemList = cartItemMapper.selectByUserIdAndCartItemIds(userId, itemIds);
        if (CollectionUtil.isEmpty(cartItemList)) {
            throw new MallException(ResultEnum.DATA_NOT_EXIST.getResult());
        }
        GetShoppingCartListResp listResp = parseData(cartItemList);
        return Optional.ofNullable(listResp).map(GetShoppingCartListResp::getList).orElseThrow(() -> new MallException(ResultEnum.DATA_PARSE_FAIL.getResult()));
    }


    private GetShoppingCartListResp parseData(List<ShoppingCartItem> cartItems) {
        if (CollectionUtil.isNotEmpty(cartItems)) {
            GetShoppingCartListResp listResp = new GetShoppingCartListResp();
            List<Long> goodIds = cartItems.stream().map(ShoppingCartItem::getGoodsId).collect(Collectors.toList());
            List<GoodsInfo> goodsInfos = goodsInfoMapper.selectByGoodIds(goodIds);
            Map<Long, GoodsInfo> goodsMap = new HashMap<>();
            if (CollectionUtil.isNotEmpty(goodsInfos)) {
                goodsMap = goodsInfos.stream().collect(Collectors.toMap(GoodsInfo::getId, Function.identity(), (entity1, entity2) -> entity1));
            }
            List<ShoppingCartContentVo> contentList = new ArrayList<>();
            for (ShoppingCartItem cartItem : cartItems) {
                ShoppingCartContentVo contentVo = new ShoppingCartContentVo();
                BeanUtil.copyProperties(cartItem, contentVo);
                contentVo.setCartItemId(cartItem.getId());
                if (goodsMap.containsKey(cartItem.getGoodsId())) {
                    GoodsInfo goodsInfo = goodsMap.get(cartItem.getGoodsId());
                    contentVo.setGoodsCoverImg(goodsInfo.getGoodsCoverImg());
                    String goodsName = goodsInfo.getGoodsName();
                    if (goodsName.length() > 28) {
                        goodsName = goodsName.substring(0, 28) + "...";
                    }
                    contentVo.setGoodsName(goodsName);
                    contentVo.setSellingPrice(goodsInfo.getSellingPrice());
                    contentList.add(contentVo);
                }
            }
            listResp.setList(contentList);
            return listResp;
        }
        return null;
    }
}
