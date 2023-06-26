package com.zqz.mall.service;

import com.zqz.mall.common.bean.AddShoppingCartReq;
import com.zqz.mall.common.bean.GetShoppingCartListResp;
import com.zqz.mall.common.bean.PageResult;
import com.zqz.mall.common.bean.ShoppingCartContentVo;

import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: ShoppingCartService
 * @Date: Created in 10:22 2023-6-26
 */
public interface ShoppingCartService {

    GetShoppingCartListResp getList(Long userId);

    void addGoods(AddShoppingCartReq req, Long userId);

    PageResult<ShoppingCartContentVo> getListPage(Integer pageNumber, Long userId);

    List<ShoppingCartContentVo> getShoppingCartItem(List<Long> itemIds, Long userId);
}
