package com.zqz.mall.controller;

import com.zqz.mall.annotation.UserToken;
import com.zqz.mall.common.bean.AddUserAddressReq;
import com.zqz.mall.common.bean.R;
import com.zqz.mall.common.bean.UpdateAddressReq;
import com.zqz.mall.entity.MallUser;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return R.successData(addressService.getByAddressId(addressId, mallUser.getId()));
    }


    /**
     * 获取默认地址
     *
     * @param mallUser
     * @return
     */
    @GetMapping("/default")
    public R getDefault(@UserToken MallUser mallUser) {
        return R.successData(addressService.getDefault(mallUser.getId()));
    }


    /**
     * 获取地址列表
     *
     * @param mallUser
     * @return
     */
    @GetMapping("/getAddressList")
    public R getAddressList(@UserToken MallUser mallUser) {
        return R.successData(addressService.getAddressList(mallUser.getId()));
    }


    /**
     * 新增地址
     *
     * @param req
     * @param mallUser
     * @return
     */
    @PostMapping("/addAddress")
    public R addAddress(@RequestBody AddUserAddressReq req, @UserToken MallUser mallUser) {
        boolean a = addressService.addAddress(req, mallUser.getId());
        if (a) {
            return R.success();
        }
        return R.fail(ResultEnum.OPERATE_ERROR.getResult());
    }


    /**
     * 更新地址
     * @param req
     * @param mallUser
     * @return
     */
    @PostMapping("/updateAddress")
    public R updateAddress(@RequestBody UpdateAddressReq req, @UserToken MallUser mallUser) {
        boolean u = addressService.updateAddress(req, mallUser.getId());
        if (u) {
            return R.success();
        }
        return R.fail(ResultEnum.OPERATE_ERROR.getResult());
    }
}
