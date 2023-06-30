package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: ZQZ
 * @Description: 订单项内容
 * @ClassName: OrderItemVo
 * @Date: Created in 9:25 2023-6-30
 */
@Data
public class OrderItemVo implements Serializable {
    private static final long serialVersionUID = -4701653612824314625L;

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
    private BigDecimal sellingPrice;
}
