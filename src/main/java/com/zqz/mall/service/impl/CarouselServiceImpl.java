package com.zqz.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.zqz.mall.common.bean.IndexCarouselVo;
import com.zqz.mall.dao.CarouselMapper;
import com.zqz.mall.entity.Carousel;
import com.zqz.mall.service.CarouselService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: CarouselServiceImpl
 * @Date: Created in 10:08 2023-6-13
 */
@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselMapper carouselMapper;

    @Override
    public Carousel selectByPrimaryKey(Integer id) {
        return carouselMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IndexCarouselVo> selectIndexByLimit(Integer num) {
        List<IndexCarouselVo> result = new ArrayList<>();
        List<Carousel> carouselList = carouselMapper.selectByNum(num);
        if (CollectionUtil.isNotEmpty(carouselList)) {
            result = BeanUtil.copyToList(carouselList, IndexCarouselVo.class);
        }
        return result;
    }
}
