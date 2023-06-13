package com.zqz.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallUserToken implements Serializable {
    private static final long serialVersionUID = -7498450063279524679L;

    private Long id;

    private String token;

    private Date updateTime;

    private Date expireTime;
}
