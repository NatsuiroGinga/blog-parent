package com.mszlu.blog.service;

import com.mszlu.blog.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.PageParams;

/**
* @author 17400
* @description 针对表【ms_article】的数据库操作Service
* @createDate 2022-07-03 23:48:29
*/
public interface ArticleService {

    /**
     * 分页查询文章列表
     * @author ginga
     * @Date 4/7/2022 下午11:15
     * @param pageParams 页参数
     * @return com.mszlu.blog.vo.Result
     **/
    Result listArticle(PageParams pageParams);

    /**
     * 最热文章
     * @author ginga
     * @Date 4/9/2022 上午10:22
     * @param limit 条数
     * @return com.mszlu.blog.vo.Result
     **/
    Result hotArticle(int limit);

    /**
     * 最新文章
     * @author ginga
     * @Date 4/9/2022 下午11:20
     * @param limit 条数限制
     * @return com.mszlu.blog.vo.Result
     **/
    Result newArticles(int limit);

    /**
     * 文章归档
     * @author ginga
     * @Date 4/9/2022 上午11:14
     * @return com.mszlu.blog.vo.Result
     **/
    Result listArchives();
}
