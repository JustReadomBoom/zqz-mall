package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 订单分页查询参数
 * @ClassName: OrderListPageParam
 * @Date: Created in 10:03 2023-6-30
 */
@Data
public class OrderListPageParam implements Serializable {
    private static final long serialVersionUID = 6786963021404781399L;

    private String orderNo;

    private Long userId;

    private Byte payType;

    private Byte orderStatus;

    private Byte isDeleted;

    private String startTime;

    private String endTime;



}
