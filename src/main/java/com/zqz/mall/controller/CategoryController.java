package com.zqz.mall.controller;

import com.zqz.mall.annotation.UserToken;
import com.zqz.mall.common.bean.R;
import com.zqz.mall.entity.MallUser;
import com.zqz.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: CategoryController
 * @Date: Created in 9:29 2023-6-29
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/getData")
    public R getCategoryData(@UserToken MallUser mallUser) {
        return R.successData(categoryService.getCategoryData());
    }
}
