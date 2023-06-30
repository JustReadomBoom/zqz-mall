package com.zqz.mall.controller;

import cn.hutool.core.util.StrUtil;
import com.zqz.mall.annotation.UserToken;
import com.zqz.mall.common.bean.CreateOrderReq;
import com.zqz.mall.common.bean.OrderContentVo;
import com.zqz.mall.common.bean.PageResult;
import com.zqz.mall.common.bean.R;
import com.zqz.mall.entity.MallUser;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: OrderController
 * @Date: Created in 9:20 2023-6-30
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 分页获取订单列表
     *
     * @param pageNumber
     * @param mallUser
     * @return
     */
    @GetMapping("/getListPage")
    public R getListPage(@RequestParam("pageNumber") Integer pageNumber,
                         @RequestParam("status") Integer status,
                         @UserToken MallUser mallUser) {
        PageResult<OrderContentVo> page = orderService.getListPage(pageNumber, status, mallUser.getId());
        return R.successData(page);
    }


    /**
     * 创建订单
     *
     * @param req
     * @param mallUser
     * @return
     */
    @PostMapping("/createOrder")
    public R createOrder(@RequestBody @Valid CreateOrderReq req, @UserToken MallUser mallUser) {
        String orderNo = orderService.createOrder(req, mallUser.getId());
        if (StrUtil.isBlank(orderNo)) {
            return R.fail(ResultEnum.OPERATE_ERROR.getResult());
        }
        return R.successData(orderNo);
    }


}
