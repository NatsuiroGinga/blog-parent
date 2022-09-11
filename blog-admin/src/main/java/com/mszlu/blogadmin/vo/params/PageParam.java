package com.mszlu.blogadmin.vo.params;

import lombok.Data;

/**
 * @author ginga
 * @version 1.0
 * @ClassName PageParams
 * @Date 11/9/2022 下午7:50
 */
@Data
public class PageParam {

    private Integer currentPage;

    private Integer pageSize;

    private String queryString;
}
