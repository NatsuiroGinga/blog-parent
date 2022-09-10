package com.mszlu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mszlu.blog.pojo.Tag;
import com.mszlu.blog.service.TagService;
import com.mszlu.blog.mapper.TagMapper;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.TagVo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 17400
* @description 针对表【ms_tag】的数据库操作Service实现
* @createDate 2022-07-03 23:48:59
*/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {

        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);

        return copyList(tags);
    }

    @Override
    public Result hots(int limit) {

        /*
        * 1. 标签所拥有文章数量最多, 最热标签
        * 2. 查询 根据tag_id 分组 计数, 从大到小排列 取前limit个
        * */
        List<Long> tagIds = tagMapper.findHotsTagIds(limit);

        if (CollectionUtils.isEmpty(tagIds)) {
            return Result.success(Collections.emptyList());
        }

        // 需要tagId, tagName 的 Tag对象
        List<Tag> tagList = tagMapper.findTagsByArticleIds(tagIds);

        return Result.success(tagList);
    }

    @Override
    public Result finAll() {
        final LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getTagName);
        final List<Tag> tagList = tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tagList));
    }

    @Override
    public Result findAllDetail() {
        final List<Tag> tagList = tagMapper.selectList(new LambdaQueryWrapper<>());
        return Result.success(tagList);
    }

    private List<TagVo> copyList(@NotNull List<Tag> tags) {

        return tags.stream()
                .map(this::copy)
                .collect(Collectors.toList());
    }

    @NotNull
    private TagVo copy(Tag tag) {

        final TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);

        return tagVo;
    }

}
