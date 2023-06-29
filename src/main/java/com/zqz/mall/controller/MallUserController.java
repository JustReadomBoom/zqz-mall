package com.zqz.mall.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.zqz.mall.annotation.UserToken;
import com.zqz.mall.common.bean.*;
import com.zqz.mall.entity.MallUser;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/getInfo")
    public R getInfo(@UserToken MallUser mallUser) {
        GetUserInfoVo user = new GetUserInfoVo();
        BeanUtil.copyProperties(mallUser, user);
        return R.successData(mallUser);
    }


    @PostMapping("/updateInfo")
    public R updateInfo(@RequestBody UpdateUserInfoReq req, @UserToken MallUser mallUser) {
        boolean up = mallUserService.updateInfo(req, mallUser.getId());
        if (up) {
            return R.success();
        }
        return R.fail(ResultEnum.OPERATE_ERROR.getResult());
    }
}
