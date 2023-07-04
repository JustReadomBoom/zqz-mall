package com.zqz.mall.controller;

import com.zqz.mall.annotation.UserToken;
import com.zqz.mall.common.bean.R;
import com.zqz.mall.entity.MallUser;
import com.zqz.mall.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    /**
     * 获取商品详情
     * @param goodsId
     * @param mallUser
     * @return
     */
    @GetMapping("/detail/{goodsId}")
    public R getDetailByGoodsId(@PathVariable("goodsId") Long goodsId,
                                @UserToken MallUser mallUser) {
        return R.successData(goodsService.getDetailByGoodsId(goodsId));
    }


    /**
     * 搜索商品
     * @param keyword
     * @param goodsCategoryId
     * @param orderBy
     * @param pageNumber
     * @param mallUser
     * @return
     */
    @GetMapping("/searchGoods")
    public R searchGoods(@RequestParam String keyword,
                         @RequestParam Long goodsCategoryId,
                         @RequestParam String orderBy,
                         @RequestParam Integer pageNumber,
                         @UserToken MallUser mallUser) {
        return R.successData(goodsService.searchGoods(keyword, goodsCategoryId, orderBy, pageNumber));
    }

}
