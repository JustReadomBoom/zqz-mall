package com.zqz.mall.service;

import com.zqz.mall.common.bean.IndexCarouselVo;
import com.zqz.mall.entity.Carousel;

import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: CarouselService
 * @Date: Created in 10:08 2023-6-13
 */
public interface CarouselService {

    Carousel selectByPrimaryKey(Integer id);



    List<IndexCarouselVo> selectIndexByLimit(Integer num);


}
