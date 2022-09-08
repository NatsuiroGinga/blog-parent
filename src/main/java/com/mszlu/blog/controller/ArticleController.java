package com.mszlu.blog.controller;

import com.mszlu.blog.service.ArticleService;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.ArticleParam;
import com.mszlu.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author ginga
 * @version 1.0
 * @ClassName ArticleController
 * @Date 3/7/2022 下午11:50
 */
@RequestMapping("articles")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 首页文章列表
     * @author ginga
     * @Date 3/9/2022 下午9:17
     * @param pageParams 页面参数
     * @return com.mszlu.blog.vo.Result
     **/
    @PostMapping
    public Result listArticle(@RequestBody PageParams pageParams) {
        return articleService.listArticle(pageParams);
    }

    /**
     * 首页最热文章
     * @author ginga
     * @Date 4/9/2022 上午10:21
     * @return com.mszlu.blog.vo.Result
     **/
    @PostMapping("hot")
    public Result hotArticle() {
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    @PostMapping("new")
    public Result newArticles() {
        int limit = 5;
        return articleService.newArticles(limit);
    }

    @PostMapping("listArchives")
    public Result listArchives() {
        return articleService.listArchives();
    }

    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId) {
        return articleService.findArticleById(articleId);
    }

    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam) {
        return articleService.publish(articleParam);
    }
}
