package com.zqz.mall.service;

import com.zqz.mall.common.bean.UserAddressVo;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: AddressService
 * @Date: Created in 17:06 2023-6-26
 */
public interface AddressService {
    UserAddressVo getByAddressId(Long addressId, Long userId);

    UserAddressVo getDefault(Long userId);
}
