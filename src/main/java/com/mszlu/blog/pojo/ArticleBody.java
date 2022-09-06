package com.mszlu.blog.pojo;

import lombok.Data;

/**
 * @author ginga
 * @version 1.0
 * @ClassName ArticleBody
 * @Date 6/9/2022 下午5:21
 */
@Data
public class ArticleBody {

    private Long id;

    private String content;

    private String contentHtml;

    private Long articleId;

}
