package com.zqz.mall.controller;

import com.zqz.mall.annotation.UserToken;
import com.zqz.mall.common.bean.AddShoppingCartReq;
import com.zqz.mall.common.bean.PageResult;
import com.zqz.mall.common.bean.R;
import com.zqz.mall.common.bean.ShoppingCartContentVo;
import com.zqz.mall.entity.MallUser;
import com.zqz.mall.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: ShoppingCartController
 * @Date: Created in 10:15 2023-6-26
 */
@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 获取购物车列表
     *
     * @param mallUser
     * @return
     */
    @GetMapping("/list")
    public R getList(@UserToken MallUser mallUser) {
        return R.successData(shoppingCartService.getList(mallUser.getId()));
    }


    /**
     * 分页获取购物车列表
     *
     * @param pageNumber
     * @param mallUser
     * @return
     */
    @GetMapping("/listPage")
    public R getListPage(Integer pageNumber, @UserToken MallUser mallUser) {
        PageResult<ShoppingCartContentVo> page = shoppingCartService.getListPage(pageNumber, mallUser.getId());
        return R.successData(page);
    }

    /**
     * 添加商品至购物车
     *
     * @param req
     * @param mallUser
     * @return
     */
    @PostMapping("/addGoods")
    public R addGoods(@RequestBody AddShoppingCartReq req, @UserToken MallUser mallUser) {
        shoppingCartService.addGoods(req, mallUser.getId());
        return R.success();
    }

    /**
     * 获取购物车详情信息
     * @param cartItemIds
     * @param mallUser
     * @return
     */
    @GetMapping("/getShoppingCartItem")
    public R getShoppingCartItem(Long[] cartItemIds, @UserToken MallUser mallUser) {
        List<ShoppingCartContentVo> cartContentVos = shoppingCartService.getShoppingCartItem(Arrays.asList(cartItemIds), mallUser.getId());
        return R.successData(cartContentVos);
    }

}
