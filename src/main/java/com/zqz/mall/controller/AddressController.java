package com.zqz.mall.controller;

import com.zqz.mall.annotation.UserToken;
import com.zqz.mall.common.bean.R;
import com.zqz.mall.common.bean.UserAddressVo;
import com.zqz.mall.entity.MallUser;
import com.zqz.mall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: AddressController
 * @Date: Created in 17:04 2023-6-26
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;


    /**
     * 根据id查询地址信息
     *
     * @param addressId
     * @param mallUser
     * @return
     */
    @GetMapping("/getByAddressId/{addressId}")
    public R getByAddressId(@PathVariable("addressId") Long addressId, @UserToken MallUser mallUser) {
        UserAddressVo addressVo = addressService.getByAddressId(addressId, mallUser.getId());
        return R.successData(addressVo);
    }


    /**
     * 获取默认地址
     * @param mallUser
     * @return
     */
    @GetMapping("/default")
    public R getDefault(@UserToken MallUser mallUser) {
        UserAddressVo addressVo = addressService.getDefault(mallUser.getId());
        return R.successData(addressVo);
    }
}
