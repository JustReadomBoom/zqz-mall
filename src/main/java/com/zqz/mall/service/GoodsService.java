package com.zqz.mall.service;

import com.zqz.mall.common.bean.GoodsDetailResp;
import com.zqz.mall.common.bean.PageResult;
import com.zqz.mall.common.bean.SearchGoodsListResp;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: GoodsService
 * @Date: Created in 14:24 2023-6-26
 */
public interface GoodsService {

    GoodsDetailResp getDetailByGoodsId(Long goodsId);


    PageResult<SearchGoodsListResp> searchGoods(String keyword, Long goodsCategoryId, String orderBy, Integer pageNumber);
}
