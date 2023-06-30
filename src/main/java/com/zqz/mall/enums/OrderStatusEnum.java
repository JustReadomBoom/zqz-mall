package com.zqz.mall.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: OrderStatusEnum
 * @Date: Created in 9:39 2023-6-30
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    DEFAULT(-9, "ERROR"),
    ORDER_PRE_PAY(0, "待支付"),
    ORDER_PAID(1, "已支付"),
    ORDER_PACKAGED(2, "配货完成"),
    ORDER_EXPRESS(3, "出库成功"),
    ORDER_SUCCESS(4, "交易成功"),
    ORDER_CLOSED_BY_MALLUSER(-1, "手动关闭"),
    ORDER_CLOSED_BY_EXPIRED(-2, "超时关闭"),
    ORDER_CLOSED_BY_JUDGE(-3, "商家关闭");

    private int status;

    private String name;

    public static OrderStatusEnum getByStatus(int status) {
        if (ObjectUtil.isEmpty(status)) {
            return DEFAULT;
        }
        for (OrderStatusEnum ose : OrderStatusEnum.values()) {
            if (ose.status == status) {
                return ose;
            }
        }
        return DEFAULT;
    }


}
