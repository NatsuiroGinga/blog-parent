package com.mszlu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mszlu.blog.mapper.CategoryMapper;
import com.mszlu.blog.pojo.Category;
import com.mszlu.blog.service.CategoryService;
import com.mszlu.blog.vo.CategoryVo;
import com.mszlu.blog.vo.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public CategoryVo findCategoryById(Long categoryId) {

        final Category category = categoryMapper.selectById(categoryId);
        final CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);

        return categoryVo;
    }

    @Override
    public Result findAll() {
        final LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getId, Category::getCategoryName);
        List<Category> categories = categoryMapper.selectList(queryWrapper);

        return Result.success(copyList(categories));
    }

    @Override
    public Result findAllDetail() {

        final LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        final List<Category> categories = categoryMapper.selectList(queryWrapper);

        return Result.success(categories);
    }

    @Override
    public Result categoryDetailById(Long id) {

        final Category category = categoryMapper.selectById(id);

        return Result.success(category);
    }

    private List<CategoryVo> copyList(@NotNull List<Category> categories) {
        return categories.stream()
                .map(this::copy)
                .collect(Collectors.toList());
    }

    @NotNull
    private CategoryVo copy(Category category) {
        final CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }

}
