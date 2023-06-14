package com.zqz.mall.service;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: MallUserServiceImpl
 * @Date: Created in 14:14 2023-6-14
 */
public interface MallUserService {

    String doLogin(String name, String password);

    String doRegister(String name, String password);
}
