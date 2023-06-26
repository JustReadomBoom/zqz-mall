package com.zqz.mall.dao;

import com.zqz.mall.entity.ShoppingCartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCartItem record);

    int insertSelective(ShoppingCartItem record);

    ShoppingCartItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingCartItem record);

    int updateByPrimaryKey(ShoppingCartItem record);



    List<ShoppingCartItem> selectByUserId(@Param("userId") Long userId, @Param("num") int num);

    List<ShoppingCartItem> selectByUserIdPage(@Param("userId") Long userId);

    ShoppingCartItem selectByUserIdAndGoodsId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    int selectCountByUserId(@Param("userId") Long userId);

    List<ShoppingCartItem> selectByUserIdAndCartItemIds(@Param("userId") Long userId, @Param("itemIds") List<Long> itemIds);
}
