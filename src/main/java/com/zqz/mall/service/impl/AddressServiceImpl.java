package com.zqz.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zqz.mall.common.bean.AddUserAddressReq;
import com.zqz.mall.common.bean.UpdateAddressReq;
import com.zqz.mall.common.bean.UserAddressVo;
import com.zqz.mall.dao.MallUserAddressMapper;
import com.zqz.mall.entity.MallUserAddress;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.exception.MallException;
import com.zqz.mall.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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


    @Override
    public List<UserAddressVo> getAddressList(Long userId) {
        List<MallUserAddress> addressList = userAddressMapper.selectAddressList(userId);
        if (CollectionUtil.isEmpty(addressList)) {
            throw new MallException(ResultEnum.DATA_NOT_EXIST.getResult());
        }
        List<UserAddressVo> resultList = new ArrayList<>();
        addressList.forEach(a -> {
            UserAddressVo addressVo = new UserAddressVo();
            BeanUtil.copyProperties(a, addressVo);
            addressVo.setAddressId(a.getId());
            resultList.add(addressVo);
        });
        return resultList.stream().sorted(Comparator.comparing(UserAddressVo::getDefaultFlag).reversed()).collect(Collectors.toList());
    }

    @Override
    public boolean addAddress(AddUserAddressReq req, Long userId) {
        MallUserAddress newAddress = new MallUserAddress();
        BeanUtil.copyProperties(req, newAddress);
        newAddress.setUserId(userId);
        return doAddAddress(newAddress, userId);
    }

    @Override
    public boolean updateAddress(UpdateAddressReq req, Long userId) {
        MallUserAddress address = userAddressMapper.selectByPrimaryKey(req.getAddressId());
        if (ObjectUtil.isEmpty(address)) {
            throw new MallException(ResultEnum.DATA_NOT_EXIST.getResult());
        }
        if (!userId.equals(address.getUserId())) {
            throw new MallException(ResultEnum.REQUEST_FORBIDEN_ERROR.getResult());
        }
        MallUserAddress newAddress = new MallUserAddress();
        BeanUtil.copyProperties(req, newAddress);
        newAddress.setId(req.getAddressId());
        newAddress.setUserId(userId);
        return doUpdateAddress(newAddress, userId);
    }


    private boolean doUpdateAddress(MallUserAddress address, Long userId) {
        MallUserAddress oldAddress = userAddressMapper.selectByPrimaryKey(address.getId());
        Date date = new Date();
        if (address.getDefaultFlag().intValue() == 1) {
            //添加默认地址，需要将原有的默认地址修改掉
            MallUserAddress defaultAddress = userAddressMapper.getMyDefaultAddress(userId);
            if (ObjectUtil.isNotEmpty(defaultAddress) && !defaultAddress.getId().equals(oldAddress.getId())) {
                defaultAddress.setDefaultFlag((byte) 0);
                defaultAddress.setUpdateTime(date);
                int u = userAddressMapper.updateByPrimaryKeySelective(defaultAddress);
                if (u < 1) {
                    throw new MallException(ResultEnum.DB_ERROR.getResult());
                }
            }
        }
        address.setUpdateTime(date);
        return userAddressMapper.updateByPrimaryKeySelective(address) > 0;
    }

    private boolean doAddAddress(MallUserAddress address, Long userId) {
        Date date = new Date();
        if (address.getDefaultFlag().intValue() == 1) {
            //添加默认地址，需要将原有的默认地址修改掉
            MallUserAddress defaultAddress = userAddressMapper.getMyDefaultAddress(userId);
            if (ObjectUtil.isNotEmpty(defaultAddress)) {
                defaultAddress.setDefaultFlag((byte) 0);
                defaultAddress.setUpdateTime(date);
                int u = userAddressMapper.updateByPrimaryKeySelective(defaultAddress);
                if (u < 1) {
                    throw new MallException(ResultEnum.DB_ERROR.getResult());
                }
            }
        }
        return userAddressMapper.insertSelective(address) > 0;
    }
}
