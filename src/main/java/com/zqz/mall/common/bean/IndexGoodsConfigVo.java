package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 商品配置信息
 * @ClassName: IndexGoodsConfigVo
 * @Date: Created in 15:03 2023-6-13
 */
@Data
public class IndexGoodsConfigVo implements Serializable {
    private static final long serialVersionUID = -6662524983438985838L;

    /**
     * 商品ID
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
}
