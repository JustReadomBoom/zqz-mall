package com.zqz.mall.dao;

import com.zqz.mall.entity.MallUserAddress;

public interface MallUserAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallUserAddress record);

    int insertSelective(MallUserAddress record);

    MallUserAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallUserAddress record);

    int updateByPrimaryKey(MallUserAddress record);

    MallUserAddress getMyDefaultAddress(Long userId);
}
