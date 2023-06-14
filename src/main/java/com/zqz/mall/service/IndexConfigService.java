package com.zqz.mall.service;

import com.zqz.mall.common.bean.IndexGoodsConfigVo;

import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: IndexConfigService
 * @Date: Created in 11:26 2023-6-14
 */
public interface IndexConfigService {
    List<IndexGoodsConfigVo> selectByTypeAndLimit(Integer type, Integer num);
}
