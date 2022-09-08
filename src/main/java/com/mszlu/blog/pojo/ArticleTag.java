package com.mszlu.blog.pojo;

import lombok.Data;

/**
 * @author ginga
 * @version 1.0
 * @ClassName ArticleTag
 * @Date 8/9/2022 下午5:10
 */
@Data
public class ArticleTag {

    private Long id;

    private Long articleId;

    private Long tagId;
}
