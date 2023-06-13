package com.zqz.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallUser implements Serializable {
    private static final long serialVersionUID = -654450232973383491L;

    private Long id;

    private String nickName;

    private String loginName;

    private String passwordMd5;

    private String introduceSign;

    private Byte isDeleted;

    private Byte lockedFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
