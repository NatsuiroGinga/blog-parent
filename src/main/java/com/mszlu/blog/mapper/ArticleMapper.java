package com.mszlu.blog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mszlu.blog.dos.Archives;
import com.mszlu.blog.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 17400
* @description 针对表【ms_article】的数据库操作Mapper
* @createDate 2022-07-03 23:48:29
* @Entity com.mszlu.blog.pojo.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<Archives> listArchives();

    IPage<Article> listArticle(Page<Article> page,
                               @Param("categoryId") Long categoryId,
                               @Param("tagId") Long tagId,
                               @Param("year") String year,
                               @Param("month") String month);
}




