package com.zqz.mall.controller;

import cn.hutool.json.JSONUtil;
import com.zqz.mall.entity.Carousel;
import com.zqz.mall.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: TestController
 * @Date: Created in 17:09 2023-6-12
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CarouselService carouselService;

    @GetMapping("/carousel")
    public Object testCarousel() {
        Carousel carousel = carouselService.selectByPrimaryKey(2);
        return JSONUtil.toJsonStr(carousel);
    }

}
