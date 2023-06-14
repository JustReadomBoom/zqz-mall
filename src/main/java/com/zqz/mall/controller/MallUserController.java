package com.zqz.mall.controller;

import cn.hutool.core.util.StrUtil;
import com.zqz.mall.common.bean.MallUserLoginReq;
import com.zqz.mall.common.bean.MallUserRegisterReq;
import com.zqz.mall.common.bean.R;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: MallUserController
 * @Date: Created in 13:51 2023-6-14
 */
@RestController
@RequestMapping("/user")
public class MallUserController {
    @Autowired
    private MallUserService mallUserService;


    @PostMapping("/login")
    public R login(@RequestBody @Valid MallUserLoginReq req) {
        String result = mallUserService.doLogin(req.getLoginName(), req.getPasswordMd5());
        if (StrUtil.isNotBlank(result)) {
            return R.successData(result);
        }
        return R.fail(ResultEnum.LOGIN_ERROR.getResult());
    }


    @PostMapping("/register")
    public R register(@RequestBody @Valid MallUserRegisterReq req) {
        String result = mallUserService.doRegister(req.getLoginName(), req.getPassword());
        if (StrUtil.isNotBlank(result)) {
            return R.success();
        }
        return R.fail(ResultEnum.REGISTER_ERROR.getResult());
    }
}
