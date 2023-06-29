package com.zqz.mall.service;

import com.zqz.mall.common.bean.AddUserAddressReq;
import com.zqz.mall.common.bean.UpdateAddressReq;
import com.zqz.mall.common.bean.UserAddressVo;

import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: AddressService
 * @Date: Created in 17:06 2023-6-26
 */
public interface AddressService {
    UserAddressVo getByAddressId(Long addressId, Long userId);

    UserAddressVo getDefault(Long userId);

    List<UserAddressVo> getAddressList(Long userId);

    boolean addAddress(AddUserAddressReq req, Long userId);

    boolean updateAddress(UpdateAddressReq req, Long userId);
}
