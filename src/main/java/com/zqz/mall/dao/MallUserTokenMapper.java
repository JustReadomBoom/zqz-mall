package com.zqz.mall.dao;

import com.zqz.mall.entity.MallUserToken;

public interface MallUserTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallUserToken record);

    int insertSelective(MallUserToken record);

    MallUserToken selectByPrimaryKey(Long id);

    MallUserToken selectByToken(String token);
}
