package com.zqz.mall.controller;

import cn.hutool.json.JSONUtil;
import com.zqz.mall.annotation.UserToken;
import com.zqz.mall.common.bean.IndexCarouselVo;
import com.zqz.mall.common.bean.IndexInfo;
import com.zqz.mall.common.bean.R;
import com.zqz.mall.entity.MallUser;
import com.zqz.mall.service.CarouselService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: IndexInfoController
 * @Date: Created in 15:08 2023-6-13
 */
@RestController
@RequestMapping("/index")
@Slf4j
public class IndexInfoController {

    @Autowired
    private CarouselService carouselService;


    /**
     * 获取首页(轮播图、新品、推荐)数据
     *
     * @return
     */
    @GetMapping("/getIndexInfo")
    public R getIndexInfo(@UserToken MallUser mallUser) {
        log.info("用户:{}", JSONUtil.toJsonStr(mallUser));
        IndexInfo indexInfo = new IndexInfo();
        List<IndexCarouselVo> carouselVos = carouselService.selectIndexByLimit(5);
        indexInfo.setCarousels(carouselVos);
        return R.success(indexInfo);

    }


}
