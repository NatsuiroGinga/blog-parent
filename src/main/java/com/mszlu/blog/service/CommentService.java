package com.mszlu.blog.service;

import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.CommentParam;

/**
 * @author ginga
 * @version 1.0
 * @ClassName CommentService
 * @Date 8/9/2022 上午9:09
 */
public interface CommentService {

    /**
     * 根据文章id查询所有评论
     * @author ginga
     * @Date 8/9/2022 上午9:12
     * @param id 文章id
     * @return com.mszlu.blog.vo.Result
     **/
    Result commentsById(Long id);

    /**
     * 评论
     * @author ginga
     * @Date 8/9/2022 上午9:52
     * @param commentParam 评论
     * @return com.mszlu.blog.vo.Result
     **/
    Result comment(CommentParam commentParam);
}
