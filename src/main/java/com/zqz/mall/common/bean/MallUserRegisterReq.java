package com.zqz.mall.common.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 用户注册入参
 * @ClassName: MallUserRegisterReq
 * @Date: Created in 14:53 2023-6-14
 */
@Data
public class MallUserRegisterReq implements Serializable {


    private static final long serialVersionUID = 4964291949607290650L;

    /**
     * 登录名
     */
    @NotBlank(message = "登录名不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$", message = "请输入正确的登录名")
    private String loginName;

    /**
     * 密码 明文
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
