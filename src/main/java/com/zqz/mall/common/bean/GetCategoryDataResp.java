package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description: 分类数据
 * @ClassName: GetCategoryDataResp
 * @Date: Created in 9:31 2023-6-29
 */
@Data
public class GetCategoryDataResp implements Serializable {

    private static final long serialVersionUID = -173985626747065375L;

    private List<GetCategoryDataVo> data;

}
