package com.zqz.mall.dao;

import com.zqz.mall.entity.OrderAddress;

public interface OrderAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderAddress record);

    int insertSelective(OrderAddress record);

    OrderAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderAddress record);

    int updateByPrimaryKey(OrderAddress record);
}