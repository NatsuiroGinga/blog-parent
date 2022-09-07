package com.mszlu.blog.service;

import com.mszlu.blog.vo.CategoryVo;

/**
 * @author ginga
 * @version 1.0
 * @ClassName CategoryService
 * @Date 7/9/2022 下午6:11
 */
public interface CategoryService {


    CategoryVo findCategoryById(Integer categoryId);
}
