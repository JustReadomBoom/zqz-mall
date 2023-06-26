package com.zqz.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zqz.mall.common.bean.UserAddressVo;
import com.zqz.mall.dao.MallUserAddressMapper;
import com.zqz.mall.entity.MallUserAddress;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.exception.MallException;
import com.zqz.mall.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: AddressServiceImpl
 * @Date: Created in 17:06 2023-6-26
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private MallUserAddressMapper userAddressMapper;


    @Override
    public UserAddressVo getByAddressId(Long addressId, Long userId) {
        MallUserAddress address = userAddressMapper.selectByPrimaryKey(addressId);
        if (ObjectUtil.isEmpty(address)) {
            throw new MallException(ResultEnum.DATA_NOT_EXIST.getResult());
        }
        UserAddressVo addressVo = new UserAddressVo();
        BeanUtil.copyProperties(address, addressVo);
        addressVo.setAddressId(address.getId());
        if (!userId.equals(address.getUserId())) {
            throw new MallException(ResultEnum.REQUEST_FORBIDEN_ERROR.getResult());
        }
        return addressVo;
    }

    @Override
    public UserAddressVo getDefault(Long userId) {
        MallUserAddress address = userAddressMapper.getMyDefaultAddress(userId);
        if (ObjectUtil.isEmpty(address)) {
            throw new MallException(ResultEnum.DATA_NOT_EXIST.getResult());
        }
        UserAddressVo addressVo = new UserAddressVo();
        BeanUtil.copyProperties(address, addressVo);
        addressVo.setAddressId(address.getId());
        return addressVo;
    }
}
