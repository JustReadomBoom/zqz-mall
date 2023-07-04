package com.zqz.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqz.mall.common.bean.*;
import com.zqz.mall.dao.GoodsInfoMapper;
import com.zqz.mall.entity.GoodsInfo;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.exception.MallException;
import com.zqz.mall.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PageResult<SearchGoodsListResp> searchGoods(String keyword, Long goodsCategoryId, String orderBy, Integer pageNumber) {
        PageResult<SearchGoodsListResp> result = new PageResult<>();
        //默认每页5条
        Page<Object> page = PageHelper.startPage(pageNumber, 5);
        SearchGoodsListParam param = new SearchGoodsListParam();
        param.setKeyword(keyword);
        param.setGoodsCategoryId(goodsCategoryId);
        param.setOrderBy(orderBy);
        List<GoodsInfo> goodsList = goodsInfoMapper.searchGoodsList(param);
        List<SearchGoodsListResp> newGoods = new ArrayList<>();
        if (CollectionUtil.isEmpty(goodsList)) {
            goodsList.forEach(g -> {
                SearchGoodsListResp goodsVo = new SearchGoodsListResp();
                goodsVo.setGoodsId(g.getId());
                goodsVo.setGoodsCoverImg(g.getGoodsCoverImg());
                goodsVo.setSellingPrice(g.getSellingPrice());

                String goodsName = g.getGoodsName();
                String goodsIntro = g.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    goodsVo.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    goodsVo.setGoodsIntro(goodsIntro);
                }
                newGoods.add(goodsVo);
            });
        }
        result.setList(newGoods);
        result.setCurrPage(pageNumber);
        result.setPageSize(5);
        result.setTotalCount((int) page.getTotal());
        result.setTotalPage(page.getPages());
        return result;
    }
}
