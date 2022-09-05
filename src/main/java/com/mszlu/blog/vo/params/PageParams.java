package com.mszlu.blog.vo.params;

import lombok.Data;

/**
 * @author ginga
 * @version 1.0
 * @ClassName PageParams
 * @Date 3/7/2022 下午11:52
 */
@Data
public class PageParams {

    /**
     * 页码
     */
    private int page = 1;

    /**
     * 一页数量
     */
    private int pageSize = 10;

}
