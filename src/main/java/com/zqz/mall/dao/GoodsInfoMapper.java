package com.zqz.mall.dao;

import com.zqz.mall.common.bean.SearchGoodsListParam;
import com.zqz.mall.common.bean.StockNumVo;
import com.zqz.mall.entity.GoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKeyWithBLOBs(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    List<GoodsInfo> selectByGoodIds(List<Long> goodIds);

    int updateStockNum(@Param("stockNumVos") List<StockNumVo> stockNumVos);

    List<GoodsInfo> searchGoodsList(SearchGoodsListParam param);
}
