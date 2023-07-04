package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: ZQZ
 * @Description: 搜索商品出参
 * @ClassName: SearchGoodsListResp
 * @Date: Created in 9:53 2023-7-4
 */
@Data
public class SearchGoodsListResp implements Serializable {
    private static final long serialVersionUID = 1556235864453968509L;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品简介
     */
    private String goodsIntro;

    /**
     * 商品图片地址
     */
    private String goodsCoverImg;

    /**
     * 商品价格
     */
    private BigDecimal sellingPrice;


}
