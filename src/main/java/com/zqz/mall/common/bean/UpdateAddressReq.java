package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 更新地址入参
 * @ClassName: UpdateAddressReq
 * @Date: Created in 16:47 2023-6-29
 */
@Data
public class UpdateAddressReq implements Serializable {
    private static final long serialVersionUID = -9080710258460325099L;

    /**
     * 地址id
     */
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
