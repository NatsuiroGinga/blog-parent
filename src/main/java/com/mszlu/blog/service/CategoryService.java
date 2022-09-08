package com.mszlu.blog.service;

import com.mszlu.blog.vo.CategoryVo;
import com.mszlu.blog.vo.Result;

/**
 * @author ginga
 * @version 1.0
 * @ClassName CategoryService
 * @Date 7/9/2022 下午6:11
 */
public interface CategoryService {

    /**
     * 根据类别id查询类别信息
     * @author ginga
     * @Date 8/9/2022 下午4:25
     * @param categoryId 类别id
     * @return com.mszlu.blog.vo.CategoryVo
     **/

    CategoryVo findCategoryById(Long categoryId);

    /**
     * 查询所有类别
     * @author ginga
     * @Date 8/9/2022 下午4:25
     * @return com.mszlu.blog.vo.Result
     **/
    Result findAll();

}
