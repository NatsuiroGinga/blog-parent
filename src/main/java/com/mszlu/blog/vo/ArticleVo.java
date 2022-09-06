package com.mszlu.blog.vo;

import com.mszlu.blog.pojo.ArticleBody;
import com.mszlu.blog.pojo.Category;
import com.mszlu.blog.pojo.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author ginga
 * @version 1.0
 * @ClassName ArticleVo
 * @Date 4/7/2022 下午11:26
 */
@Data
public class ArticleVo {

    private Long id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;

    private String createDate;

    private String author;

    private ArticleBodyVo body;

    private List<TagVo> tags;

    private CategoryVo category;

}
