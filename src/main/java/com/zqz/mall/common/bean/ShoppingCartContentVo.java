package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 购物车内容
 * @ClassName: ShoppingCartContentVo
 * @Date: Created in 10:19 2023-6-26
 */
@Data
public class ShoppingCartContentVo implements Serializable {
    private static final long serialVersionUID = 8881578554964171378L;


    private Long cartItemId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品数量
     */
    private Integer goodsCount;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String goodsCoverImg;

    /**
     * 商品价格
     */
    private Integer sellingPrice;
}
