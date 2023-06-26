package com.zqz.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.zqz.mall.common.bean.IndexGoodsConfigVo;
import com.zqz.mall.dao.GoodsInfoMapper;
import com.zqz.mall.dao.IndexConfigMapper;
import com.zqz.mall.entity.GoodsInfo;
import com.zqz.mall.entity.IndexConfig;
import com.zqz.mall.service.IndexConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: IndexConfigServiceImpl
 * @Date: Created in 11:26 2023-6-14
 */
@Service
public class IndexConfigServiceImpl implements IndexConfigService {
    @Resource
    private IndexConfigMapper indexConfigMapper;
    @Resource
    private GoodsInfoMapper goodsInfoMapper;


    @Override
    public List<IndexGoodsConfigVo> selectByTypeAndLimit(Integer type, Integer num) {
        List<IndexGoodsConfigVo> goodsConfigVos = new ArrayList<>(num);
        List<IndexConfig> configList = indexConfigMapper.selectByTypeAndNum(type, num);
        if (CollectionUtil.isNotEmpty(configList)) {
            List<Long> goodIds = configList.stream().map(IndexConfig::getGoodsId).collect(Collectors.toList());
            List<GoodsInfo> goodsInfoList = goodsInfoMapper.selectByGoodIds(goodIds);
            if(CollectionUtil.isNotEmpty(goodsInfoList)){
                for (GoodsInfo goodsInfo : goodsInfoList) {
                    IndexGoodsConfigVo configVo = new IndexGoodsConfigVo();
                    BeanUtil.copyProperties(goodsInfo, configVo);
                    configVo.setGoodsId(goodsInfo.getId());
                    String goodsName = goodsInfo.getGoodsName();
                    String goodsIntro = goodsInfo.getGoodsIntro();
                    if (goodsName.length() > 30) {
                        goodsName = goodsName.substring(0, 30) + "...";
                        configVo.setGoodsName(goodsName);
                    }
                    if (goodsIntro.length() > 22) {
                        goodsIntro = goodsIntro.substring(0, 22) + "...";
                        configVo.setGoodsIntro(goodsIntro);
                    }
                    goodsConfigVos.add(configVo);
                }
            }
        }
        return goodsConfigVos;
    }
}
