package com.mszlu.blog.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ginga
 * @version 1.0
 * @ClassName Comment
 * @Date 8/9/2022 上午9:04
 */
@Data
@TableName("ms_comment")
public class Comment {

    private Long id;

    private String content;

    private Long createDate;

    private Long articleId;

    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Integer level;

}
