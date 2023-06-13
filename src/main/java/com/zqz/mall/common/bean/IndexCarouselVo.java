package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 轮播信息
 * @ClassName: IndexCarouselVo
 * @Date: Created in 15:00 2023-6-13
 */
@Data
public class IndexCarouselVo implements Serializable {
    private static final long serialVersionUID = -2324486611988046019L;

    /**
     * 轮播图片URL
     */
    private String carouselUrl;

    /**
     * 跳转URL
     */
    private String redirectUrl;


}
