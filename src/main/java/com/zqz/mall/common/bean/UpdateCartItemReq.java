package com.zqz.mall.common.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 更新购物车订单项入参
 * @ClassName: UpdateCartItemReq
 * @Date: Created in 16:07 2023-6-30
 */
@Data
public class UpdateCartItemReq implements Serializable {
    private static final long serialVersionUID = -4086113343536065239L;

    /**
     * 购物项id
     */
    @NotNull(message = "购物项id不能为空")
    private Long cartItemId;

    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空")
    private Integer goodsCount;

}
