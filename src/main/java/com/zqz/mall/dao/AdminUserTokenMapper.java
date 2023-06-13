package com.zqz.mall.dao;

import com.zqz.mall.entity.AdminUserToken;

public interface AdminUserTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminUserToken record);

    int insertSelective(AdminUserToken record);

    AdminUserToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUserToken record);

    int updateByPrimaryKey(AdminUserToken record);
}