package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: GetUserInfoVo
 * @Date: Created in 15:33 2023-6-29
 */
@Data
public class GetUserInfoVo implements Serializable {
    private static final long serialVersionUID = -3852540921785323490L;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户登录名
     */
    private String loginName;

    /**
     * 个性签名
     */
    private String introduceSign;

}
