package com.mszlu.blogadmin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ginga
 * @version 1.0
 * @ClassName PageResult
 * @Date 11/9/2022 下午7:57
 */
@Data
public class PageResult<T> {

    private List<T> list;

    private Long total;
}
