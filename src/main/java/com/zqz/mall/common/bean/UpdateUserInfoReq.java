package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 更新用户信息入参
 * @ClassName: UpdateUserInfoReq
 * @Date: Created in 15:41 2023-6-29
 */
@Data
public class UpdateUserInfoReq implements Serializable {

    private static final long serialVersionUID = -3852540921785323490L;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户密码(需要MD5加密)
     */
    private String passwordMd5;

    /**
     * 个性签名
     */
    private String introduceSign;


}
