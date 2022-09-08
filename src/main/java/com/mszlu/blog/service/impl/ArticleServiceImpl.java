package com.mszlu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mszlu.blog.dos.Archives;
import com.mszlu.blog.mapper.ArticleBodyMapper;
import com.mszlu.blog.pojo.Article;
import com.mszlu.blog.pojo.ArticleBody;
import com.mszlu.blog.service.*;
import com.mszlu.blog.mapper.ArticleMapper;
import com.mszlu.blog.vo.ArticleBodyVo;
import com.mszlu.blog.vo.ArticleVo;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.PageParams;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author 17400
* @description 针对表【ms_article】的数据库操作Service实现
* @createDate 2022-07-03 23:48:29
*/
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ThreadService threadService;

    @Override
    public Result listArticle(@NotNull PageParams pageParams) {

        final Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        final LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 按照时间倒序排列
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        final Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        final List<Article> records = articlePage.getRecords();
        List<ArticleVo> articleVoList = copyList(records, true, true);

        return Result.success(articleVoList);
    }

    @Override
    public Result hotArticle(int limit) {
        final LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 根据浏览量倒序排
        lambdaQueryWrapper.orderByDesc(Article::getViewCounts)
                .select(Article::getId, Article::getTitle)
                .last("limit " + limit);
        final List<Article> articles = articleMapper.selectList(lambdaQueryWrapper);

        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result newArticles(int limit) {

        final LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate)
                .select(Article::getId, Article::getTitle)
                .last("limit " + limit);
        final List<Article> articles = articleMapper.selectList(queryWrapper);

        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result listArchives() {
        final List<Archives> archivesList = articleMapper.listArchives();
        return Result.success(archivesList);
    }

    @Override
    public Result findArticleById(Long articleId) {

        /*
        * 1. 根据id查询文章信息
        * 2. 根据bodyId 和 categoryId 去关联查询
        * */
        final Article article = articleMapper.selectById(articleId);
        final ArticleVo articleVo = copy(article, true, true, true, true);
        threadService.updateArticleViewCount(articleMapper, article);
        return Result.success(articleVo);
    }

    private List<ArticleVo> copyList(@NotNull List<Article> records, boolean isTag, boolean isAuthor) {

        return records.stream()
                .map(article -> copy(article, isTag, isAuthor, false, false))
                .collect(Collectors.toList());

    }

    private List<ArticleVo> copyList(@NotNull List<Article> records, boolean isTag, boolean isAuthor,
                                     boolean isBody, boolean isCategory) {

        return records.stream()
                .map(article -> copy(article, isTag, isAuthor, isBody, isCategory))
                .collect(Collectors.toList());

    }

    @Autowired
    private CategoryService categoryService;

    @NotNull
    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {

        final ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);

        articleVo.setCreateDate(new DateTime(article.getCreateDate())
                .toString("yyyy-MM-dd HH:mm"));

        if (isTag) {
            final Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }

        if (isAuthor) {
            final Long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }

        if (isBody) {
            final Long bodyId = article.getBodyId();
            articleVo.setBody(findArticleBodyById(bodyId));
        }

        if (isCategory) {
            final Integer categoryId = article.getCategoryId();
            articleVo.setCategory(categoryService.findCategoryById(categoryId));
        }

        return articleVo;
    }

    @Autowired
    private ArticleBodyMapper articleBodyMapper;
    @NotNull
    private ArticleBodyVo findArticleBodyById(Long bodyId) {

        final ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        final ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());

        return articleBodyVo;
    }

}
