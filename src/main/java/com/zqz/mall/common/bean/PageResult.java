package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: PageResult
 * @Date: Created in 15:30 2023-6-26
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 5731631775275413928L;

    /**
     * 总记录数
     */
    private int totalCount;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 当前页数
     */
    private int currPage;

    /**
     * 列表数据
     */
    private List<T> list;
}
