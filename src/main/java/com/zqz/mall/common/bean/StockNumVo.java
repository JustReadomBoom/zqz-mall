package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: StockNumVo
 * @Date: Created in 14:01 2023-6-30
 */
@Data
public class StockNumVo implements Serializable {
    private static final long serialVersionUID = -279948045782588207L;

    private Long goodsId;

    private Integer goodsCount;
}
