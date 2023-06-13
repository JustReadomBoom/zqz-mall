package com.zqz.mall.dao;

import com.zqz.mall.entity.ShoppingCartItem;

public interface ShoppingCartItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCartItem record);

    int insertSelective(ShoppingCartItem record);

    ShoppingCartItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingCartItem record);

    int updateByPrimaryKey(ShoppingCartItem record);
}