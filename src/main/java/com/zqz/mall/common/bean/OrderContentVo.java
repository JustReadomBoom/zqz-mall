package com.zqz.mall.common.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description: 订单内容
 * @ClassName: OrderContentVo
 * @Date: Created in 9:23 2023-6-30
 */
@Data
public class OrderContentVo implements Serializable {
    private static final long serialVersionUID = -7675153247066300508L;

    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单价格
     */
    private BigDecimal totalPrice;

    /**
     * 订单支付方式
     */
    private Byte payType;

    /**
     * 订单状态码
     */
    private Byte orderStatus;

    /**
     * 订单状态
     */
    private String orderStatusString;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 订单项列表
     */
    private List<OrderItemVo> newBeeMallOrderItemVOS;
}
