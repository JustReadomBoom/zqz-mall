package com.zqz.mall.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.zqz.mall.common.bean.UpdateUserInfoReq;
import com.zqz.mall.dao.MallUserMapper;
import com.zqz.mall.dao.MallUserTokenMapper;
import com.zqz.mall.entity.MallUser;
import com.zqz.mall.entity.MallUserToken;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.exception.MallException;
import com.zqz.mall.service.MallUserService;
import com.zqz.mall.utils.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: MallUserServiceImpl
 * @Date: Created in 14:14 2023-6-14
 */
@Service
public class MallUserServiceImpl implements MallUserService {

    @Resource
    private MallUserMapper mallUserMapper;
    @Resource
    private MallUserTokenMapper mallUserTokenMapper;


    @Override
    public String doLogin(String name, String password) {
        MallUser user = mallUserMapper.selectByNameAndPwd(name, password);
        if (ObjectUtil.isNotEmpty(user)) {
            if (user.getLockedFlag() == 1) {
                throw new MallException(ResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
            }
            Long userId = user.getId();
            String token = getNewToken(System.currentTimeMillis() + "", userId);
            MallUserToken userToken = mallUserTokenMapper.selectByPrimaryKey(userId);

            Date now = new Date();
            //过期时间 48 小时
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);
            if (ObjectUtil.isEmpty(userToken)) {
                userToken = new MallUserToken();
                userToken.setId(userId);
                userToken.setToken(token);
                userToken.setUpdateTime(now);
                userToken.setExpireTime(expireTime);
                int i = mallUserTokenMapper.insertSelective(userToken);
                if (i > 0) {
                    return token;
                }
            } else {
                userToken.setToken(token);
                userToken.setUpdateTime(now);
                userToken.setExpireTime(expireTime);
                int u = mallUserTokenMapper.updateByPrimaryKeySelective(userToken);
                if (u > 0) {
                    return token;
                }
            }
        }
        return null;
    }

    @Override
    public String doRegister(String name, String password) {
        MallUser user = mallUserMapper.selectByName(name);
        if (ObjectUtil.isNotEmpty(user)) {
            throw new MallException(ResultEnum.SAME_LOGIN_NAME_EXIST.getResult());
        }
        MallUser newUser = new MallUser();
        newUser.setLoginName(name);
        newUser.setNickName(name);
        newUser.setIntroduceSign("江湖故人，相逢何必曾相识");
        String pwd = DigestUtil.md5Hex(password, "UTF-8");
        newUser.setPasswordMd5(pwd);
        int i = mallUserMapper.insertSelective(newUser);
        if (i > 0) {
            return ResultEnum.SUCCESS.getResult();
        }
        return null;
    }


    private String getNewToken(String timeStr, Long userId) {
        String src = timeStr + userId + RandomUtil.randomNumbers(4);
        return CommonUtil.genToken(src);
    }


    @Override
    public boolean updateInfo(UpdateUserInfoReq req, Long userId) {
        MallUser user = mallUserMapper.selectByPrimaryKey(userId);
        if (ObjectUtil.isEmpty(user)) {
            throw new MallException(ResultEnum.DATA_NOT_EXIST.getResult());
        }
        user.setNickName(req.getNickName());
        if (StrUtil.isNotBlank(req.getPasswordMd5())) {
            user.setPasswordMd5(req.getPasswordMd5());
        }
        user.setIntroduceSign(req.getIntroduceSign());
        int u = mallUserMapper.updateByPrimaryKeySelective(user);
        if (u > 0) {
            return true;
        }
        return false;
    }

}
