package com.mszlu.blog.vo.params;

import lombok.Data;

/**
 * @author ginga
 * @version 1.0
 * @ClassName CommentParam
 * @Date 8/9/2022 上午9:41
 */
@Data
public class CommentParam {

    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;
}
