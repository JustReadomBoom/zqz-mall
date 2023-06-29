package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 三级分类数据
 * @ClassName: ThirdLevelCategoryVo
 * @Date: Created in 14:27 2023-6-29
 */
@Data
public class ThirdLevelCategoryVo implements Serializable {

    private static final long serialVersionUID = -1424380647646598697L;

    /**
     * 当前三级分类id
     */
    private Long categoryId;

    /**
     * 当前分类级别
     */
    private Byte categoryLevel;

    /**
     * 当前三级分类名称
     */
    private String categoryName;
}
