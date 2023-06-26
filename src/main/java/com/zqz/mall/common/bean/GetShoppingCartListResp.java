package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description: 购物车列表
 * @ClassName: GetShoppingCartListResp
 * @Date: Created in 10:20 2023-6-26
 */
@Data
public class GetShoppingCartListResp implements Serializable {


    private static final long serialVersionUID = 2474463564170550459L;


    private List<ShoppingCartContentVo> list;
}
