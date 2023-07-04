package com.zqz.mall.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: PayTypeEnum
 * @Date: Created in 11:28 2023-7-4
 */
@Getter
@AllArgsConstructor
public enum PayTypeEnum {


    DEFAULT(-1, "ERROR"),
    NOT_PAY(0, "无"),
    ALI_PAY(1, "支付宝"),
    WEIXIN_PAY(2, "微信支付");

    private int payType;

    private String name;

    public static PayTypeEnum getPayByType(int type) {
        if (ObjectUtil.isEmpty(type)) {
            return DEFAULT;
        }
        for (PayTypeEnum typeEnum : PayTypeEnum.values()) {
            if (typeEnum.payType == type) {
                return typeEnum;
            }
        }
        return DEFAULT;
    }


}
