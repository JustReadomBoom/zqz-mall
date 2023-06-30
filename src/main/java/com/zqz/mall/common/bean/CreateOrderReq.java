package com.zqz.mall.common.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 创建订单入参
 * @ClassName: CreateOrderReq
 * @Date: Created in 11:07 2023-6-30
 */
@Data
public class CreateOrderReq implements Serializable {
    private static final long serialVersionUID = -8395520965239097555L;

    /**
     * 订单项id数组
     */
    @NotNull(message = "订单项id数组不能为空")
    private Long[] cartItemIds;

    /**
     * 地址id
     */
    @NotNull(message = "地址id不能为空")
    private Long addressId;
}
