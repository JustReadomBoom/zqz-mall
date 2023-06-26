package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 添加购物车请求参数
 * @ClassName: AddShoppingCartReq
 * @Date: Created in 14:47 2023-6-26
 */
@Data
public class AddShoppingCartReq implements Serializable {

    private static final long serialVersionUID = -1958077457725972354L;

    /**
     * 商品数量
     */
    private Integer goodsCount;

    /**
     * 商品id
     */
    private Long goodsId;
}
