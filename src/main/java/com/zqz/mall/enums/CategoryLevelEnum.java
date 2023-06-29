package com.zqz.mall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: CategoryLevelEnum
 * @Date: Created in 14:36 2023-6-29
 */
@Getter
@AllArgsConstructor
public enum CategoryLevelEnum {


    DEFAULT(0, "ERROR"),
    LEVEL_ONE(1, "一级分类"),
    LEVEL_TWO(2, "二级分类"),
    LEVEL_THREE(3, "三级分类");

    private int level;

    private String name;


    public static CategoryLevelEnum getOrderStatusEnumByLevel(int level) {
        for (CategoryLevelEnum newBeeMallCategoryLevelEnum : CategoryLevelEnum.values()) {
            if (newBeeMallCategoryLevelEnum.getLevel() == level) {
                return newBeeMallCategoryLevelEnum;
            }
        }
        return DEFAULT;
    }

}
