package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: SearchGoodsListParam
 * @Date: Created in 10:46 2023-7-4
 */
@Data
public class SearchGoodsListParam implements Serializable {

    private static final long serialVersionUID = -5420422132309766247L;

    private String keyword;

    private Long goodsCategoryId;

    private Integer goodsSellStatus;

    private String orderBy;
}
