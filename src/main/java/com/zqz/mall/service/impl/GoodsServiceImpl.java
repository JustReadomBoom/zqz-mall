package com.zqz.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zqz.mall.common.bean.GoodsDetailResp;
import com.zqz.mall.dao.GoodsInfoMapper;
import com.zqz.mall.entity.GoodsInfo;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.exception.MallException;
import com.zqz.mall.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: GoodsServiceImpl
 * @Date: Created in 14:24 2023-6-26
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsInfoMapper goodsInfoMapper;


    @Override
    public GoodsDetailResp getDetailByGoodsId(Long goodsId) {
        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsId);
        if (ObjectUtil.isEmpty(goodsInfo)) {
            throw new MallException(ResultEnum.GOODS_NOT_EXIST.getResult());
        }
        if (0 != goodsInfo.getGoodsSellStatus()) {
            throw new MallException(ResultEnum.GOODS_PUT_DOWN.getResult());
        }
        GoodsDetailResp resp = new GoodsDetailResp();
        BeanUtil.copyProperties(goodsInfo, resp);
        resp.setGoodsId(goodsInfo.getId());
        resp.setGoodsCarouselList(goodsInfo.getGoodsCarousel().split(","));
        return resp;
    }
}
