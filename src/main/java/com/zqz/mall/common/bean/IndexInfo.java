package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description: 首页信息
 * @ClassName: IndexInfo
 * @Date: Created in 15:00 2023-6-13
 */
@Data
public class IndexInfo implements Serializable {
    private static final long serialVersionUID = -1266416967433661478L;

    /**
     * 轮播列表
     */
    private List<IndexCarouselVo> carousels;

    /**
     * 热销商品列表
     */
    private List<IndexGoodsConfigVo> hotGoods;

    /**
     * 最新商品列表
     */
    private List<IndexGoodsConfigVo> newGoods;

    /**
     * 推荐商品列表
     */
    private List<IndexGoodsConfigVo> recommendGoods;






}
