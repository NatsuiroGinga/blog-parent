package com.mszlu.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mszlu.blog.pojo.Comment;
import lombok.Data;

import java.util.List;

/**
 * @author ginga
 * @version 1.0
 * @ClassName CommentVo
 * @Date 8/9/2022 上午9:16
 */
@Data
public class CommentVo {

    // 序列化id转化为string, 防止传到前端精度损失
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private UserVo author;

    private String content;

    private List<CommentVo> children;

    private String createDate;

    private Integer level;

    private UserVo toUser;
}
