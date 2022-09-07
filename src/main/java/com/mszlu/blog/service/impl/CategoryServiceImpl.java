package com.mszlu.blog.service.impl;

import com.mszlu.blog.mapper.CategoryMapper;
import com.mszlu.blog.pojo.Category;
import com.mszlu.blog.service.CategoryService;
import com.mszlu.blog.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ginga
 * @version 1.0
 * @ClassName CategoryServiceImpl
 * @Date 7/9/2022 下午6:11
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryVo findCategoryById(Integer categoryId) {

        final Category category = categoryMapper.selectById(categoryId);
        final CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);

        return categoryVo;
    }

}
