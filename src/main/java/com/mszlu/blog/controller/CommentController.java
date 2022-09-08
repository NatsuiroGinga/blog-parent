package com.mszlu.blog.controller;

import com.mszlu.blog.service.CommentService;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ginga
 * @version 1.0
 * @ClassName CommentController
 * @Date 8/9/2022 上午9:08
 */
@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("article/{id}")
    public Result comments(@PathVariable("id") Long id) {
        return commentService.commentsById(id);
    }

    @PostMapping("create/change")
    public Result comment(@RequestBody CommentParam commentParam) {
        return commentService.comment(commentParam);
    }
}
