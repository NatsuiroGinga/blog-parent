package com.mszlu.blog.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mszlu.blog.mapper.ArticleMapper;
import com.mszlu.blog.pojo.Article;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author ginga
 * @version 1.0
 * @ClassName ThreadService
 * @Date 7/9/2022 下午10:19
 */
@Component
public class ThreadService {
    /*
    * 操作在线程池执行
    * */
    @Async("taskExecutor")
    public void updateArticleViewCount(@NotNull ArticleMapper articleMapper, @NotNull Article article) {

        final Article articleUpdate = new Article();
        final Integer viewCounts = article.getViewCounts();
        articleUpdate.setViewCounts(viewCounts);
        final LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, article.getId())
                        .eq(Article::getViewCounts, article.getViewCounts());

        articleMapper.update(articleUpdate, updateWrapper);
    }
}
