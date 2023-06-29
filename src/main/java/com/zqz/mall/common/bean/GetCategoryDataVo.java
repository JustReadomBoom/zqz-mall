package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: GetCategoryDataVo
 * @Date: Created in 9:32 2023-6-29
 */
@Data
public class GetCategoryDataVo implements Serializable {
    private static final long serialVersionUID = -2472856567204378962L;

    /**
     * 当前一级分类id
     */
    private Long categoryId;

    /**
     * 当前分类级别
     */
    private Byte categoryLevel;

    /**
     * 当前一级分类名称
     */
    private String categoryName;

    /**
     * 二级分类列表
     */
    private List<SecondLevelCategoryVo> secondLevelCategoryVOS;
}
