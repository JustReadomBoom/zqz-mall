package com.zqz.mall.service;

import com.zqz.mall.common.bean.CreateOrderReq;
import com.zqz.mall.common.bean.GetOrderDetailResp;
import com.zqz.mall.common.bean.OrderContentVo;
import com.zqz.mall.common.bean.PageResult;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: OrderService
 * @Date: Created in 9:20 2023-6-30
 */
public interface OrderService {
    PageResult<OrderContentVo> getListPage(Integer pageNumber, Integer status, Long userId);

    String createOrder(CreateOrderReq req, Long userId);

    GetOrderDetailResp getOrderDetail(String orderNo, Long userId);
}
