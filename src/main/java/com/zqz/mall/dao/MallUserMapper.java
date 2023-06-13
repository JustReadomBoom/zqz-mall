package com.zqz.mall.dao;

import com.zqz.mall.entity.MallUser;

public interface MallUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallUser record);

    int insertSelective(MallUser record);

    MallUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallUser record);

    int updateByPrimaryKey(MallUser record);
}