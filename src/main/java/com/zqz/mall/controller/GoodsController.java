package com.zqz.mall.controller;

import com.zqz.mall.annotation.UserToken;
import com.zqz.mall.common.bean.R;
import com.zqz.mall.entity.MallUser;
import com.zqz.mall.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: GoodsController
 * @Date: Created in 14:22 2023-6-26
 */
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    @GetMapping("/detail/{goodsId}")
    public R getDetailByGoodsId(@PathVariable("goodsId") Long goodsId,
                                @UserToken MallUser mallUser) {
        return R.successData(goodsService.getDetailByGoodsId(goodsId));

    }

}
