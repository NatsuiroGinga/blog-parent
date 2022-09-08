package com.mszlu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mszlu.blog.mapper.CommentMapper;
import com.mszlu.blog.pojo.Comment;
import com.mszlu.blog.pojo.SysUser;
import com.mszlu.blog.service.CommentService;
import com.mszlu.blog.service.SysUserService;
import com.mszlu.blog.utils.UserThreadLocal;
import com.mszlu.blog.vo.CommentVo;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.UserVo;
import com.mszlu.blog.vo.params.CommentParam;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ginga
 * @version 1.0
 * @ClassName CommentServiceImpl
 * @Date 8/9/2022 上午9:09
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SysUserService sysUserService;
    @Override
    public Result commentsById(Long id) {

        /*
        * 1. 根据文章id查询评论列表
        * 2. 根据作者id查询作者信息
        * 3. 判断level = 1, 有没有子评论
        * 4. 根据评论id进行查询
        * */

        final LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, id)
                        .eq(Comment::getLevel, 1);
        final List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVos = copyList(comments);

        return Result.success(commentVos);
    }

    @Override
    public Result comment(@NotNull CommentParam commentParam) {
        final SysUser sysUser = UserThreadLocal.get();
        final Comment comment = new Comment();
        comment.setAuthorId(sysUser.getId());
        comment.setArticleId(commentParam.getArticleId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        final Long parent = commentParam.getParent();

        if (parent == null || parent == 0) {
            comment.setLevel(1);
        } else {
            comment.setLevel(2);
        }

        comment.setParentId(parent == null ? 0 : parent);
        final Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        commentMapper.insert(comment);

        return Result.success(null);
    }

    private List<CommentVo> copyList(@NotNull List<Comment> comments) {

        return comments.stream()
                .map(this::copy)
                .collect(Collectors.toList());
    }

    @NotNull
    private CommentVo copy(Comment comment) {

        final CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        // 作者信息
        final Long authorId = comment.getAuthorId();
        UserVo author = sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(author);
        // 子评论
        final Integer level = comment.getLevel();
        if (level == 1) {
            final Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildren(commentVoList);
        }
        // toUser, 给谁评论
        if (level > 1) {
            final Long toUid = comment.getToUid();
            UserVo userVo = sysUserService.findUserVoById(toUid);
            commentVo.setToUser(userVo);
        }

        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {

        final LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, id);
        queryWrapper.eq(Comment::getLevel, 2);

        return copyList(commentMapper.selectList(queryWrapper));
    }
}
