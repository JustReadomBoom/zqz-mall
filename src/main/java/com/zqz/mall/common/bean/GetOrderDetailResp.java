package com.zqz.mall.common.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description: 订单详情信息
 * @ClassName: GetOrderDetailResp
 * @Date: Created in 11:13 2023-7-4
 */
@Data
public class GetOrderDetailResp implements Serializable {

    private static final long serialVersionUID = 1670883017567639857L;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单价格
     */
    private Integer totalPrice;

    /**
     * 订单支付状态码
     */
    private Byte payStatus;

    /**
     * 订单支付方式
     */
    private Byte payType;

    /**
     * 订单支付方式
     */
    private String payTypeString;

    /**
     * 订单支付时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;

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
