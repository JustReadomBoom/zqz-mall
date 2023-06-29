package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description: 二级分类数据
 * @ClassName: SecondLevelCategoryVo
 * @Date: Created in 14:25 2023-6-29
 */
@Data
public class SecondLevelCategoryVo implements Serializable {
    private static final long serialVersionUID = -8041908116087662875L;

    /**
     * 当前二级分类id
     */
    private Long categoryId;

    /**
     * 父级分类id
     */
    private Long parentId;

    /**
     * 当前分类级别
     */
    private Byte categoryLevel;

    /**
     * 当前二级分类名称
     */
    private String categoryName;

    /**
     * 三级分类列表
     */
    private List<ThirdLevelCategoryVo> thirdLevelCategoryVOS;

}
