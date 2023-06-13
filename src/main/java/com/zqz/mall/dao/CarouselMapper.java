package com.zqz.mall.dao;

import com.zqz.mall.entity.Carousel;

import java.util.List;

public interface CarouselMapper {

    Carousel selectByPrimaryKey(Integer id);

    List<Carousel> selectByNum(Integer num);

}
