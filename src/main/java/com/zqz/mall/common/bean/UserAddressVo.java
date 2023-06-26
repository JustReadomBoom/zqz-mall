package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 用户地址信息
 * @ClassName: UserAddressVo
 * @Date: Created in 17:01 2023-6-26
 */
@Data
public class UserAddressVo implements Serializable {
    private static final long serialVersionUID = -194725208202008151L;

    private Long addressId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收件人名称
     */
    private String userName;

    /**
     * 收件人联系方式
     */
    private String userPhone;

    /**
     * 是否默认地址 0-不是 1-是
     */
    private Byte defaultFlag;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 市
     */
    private String cityName;

    /**
     * 区/县
     */
    private String regionName;

    /**
     * 详细地址
     */
    private String detailAddress;

}
