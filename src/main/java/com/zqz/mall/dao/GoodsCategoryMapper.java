package com.zqz.mall.dao;

import com.zqz.mall.entity.GoodsCategory;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);
}