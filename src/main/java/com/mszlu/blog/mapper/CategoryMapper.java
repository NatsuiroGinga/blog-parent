package com.mszlu.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mszlu.blog.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ginga
 * @version 1.0
 * @ClassName CategoryMapper
 * @Date 7/9/2022 下午6:07
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
