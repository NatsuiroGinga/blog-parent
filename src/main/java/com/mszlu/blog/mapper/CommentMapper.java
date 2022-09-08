package com.mszlu.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mszlu.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ginga
 * @version 1.0
 * @ClassName CommentMapper
 * @Date 8/9/2022 上午9:07
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
