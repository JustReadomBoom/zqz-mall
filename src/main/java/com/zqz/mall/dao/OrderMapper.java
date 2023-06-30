package com.zqz.mall.dao;

import com.zqz.mall.common.bean.OrderListPageParam;
import com.zqz.mall.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> getListPage(OrderListPageParam param);

    Long selectIdByOrderNo(@Param("orderNo") String orderNo);
}
