package com.zqz.mall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderAddress implements Serializable {
    private static final long serialVersionUID = 6489625554387218792L;

    private Long id;

    private Long orderId;

    private String userName;

    private String userPhone;

    private String provinceName;

    private String cityName;

    private String regionName;

    private String detailAddress;

}
