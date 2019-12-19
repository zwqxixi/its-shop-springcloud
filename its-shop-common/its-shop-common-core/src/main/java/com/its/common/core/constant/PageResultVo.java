package com.its.common.core.constant;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/21 15:33
 * @Description:
 */
@Data
public class PageResultVo<T> implements Serializable {
    private int pageNum;//当前页

    private int pageSize;//每页的数量

    private int size;//当前页的数量

    private int pages;//总页数

    private long total;//总条数

    private List<T> list;
}
