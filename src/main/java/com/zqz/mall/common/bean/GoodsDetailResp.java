package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 商品详情
 * @ClassName: GoodsDetailResp
 * @Date: Created in 14:25 2023-6-26
 */
@Data
public class GoodsDetailResp implements Serializable {
    private static final long serialVersionUID = -2295349652634953983L;

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
    private Integer sellingPrice;

    /**
     * 商品标签
     */
    private String tag;

    /**
     *
     */
    private String[] goodsCarouselList;

    /**
     * 商品原价
     */
    private Integer originalPrice;

    /**
     * 商品详情字段
     */
    private String goodsDetailContent;
}
