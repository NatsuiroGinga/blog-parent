package com.mszlu.blog.mapper;

import com.mszlu.blog.pojo.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 17400
* @description 针对表【ms_tag】的数据库操作Mapper
* @createDate 2022-07-03 23:48:59
* @Entity com.mszlu.blog.pojo.Tag
*/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据文章id查询标签列表
     * @author ginga
     * @Date 4/9/2022 上午8:22
     * @param articleId 文章id
     * @return java.util.List<com.mszlu.blog.pojo.Tag>
     **/
    List<Tag> findTagsByArticleId(@Param("articleId") Long articleId);

    /**
     * 查询最热的标签 前n条
     * @author ginga
     * @Date 4/9/2022 上午9:57
     * @param limit 条数
     * @return java.util.List<java.lang.Long>
     **/
    List<Long> findHotsTagIds(@Param("limit") int limit);

    List<Tag> findTagsByArticleIds(@Param("tagIds") List<Long> tagIds);
}




