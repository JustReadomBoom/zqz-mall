package com.zqz.mall.dao;

import com.zqz.mall.entity.IndexConfig;

public interface IndexConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IndexConfig record);

    int insertSelective(IndexConfig record);

    IndexConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexConfig record);

    int updateByPrimaryKey(IndexConfig record);
}