package com.mszlu.blog.service;

import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.TagVo;

import java.util.List;

/**
* @author 17400
* @description 针对表【ms_tag】的数据库操作Service
* @createDate 2022-07-03 23:48:59
*/
public interface TagService {

    /**
     * 根据文章id查询其所有标签
     * @author ginga
     * @Date 4/9/2022 下午11:23
     * @param articleId 文章id
     * @return java.util.List<com.mszlu.blog.vo.TagVo>
     **/
    List<TagVo> findTagsByArticleId(Long articleId);

    /**
     * 最热标签
     * @author ginga
     * @Date 4/9/2022 下午11:22
     * @param limit 条数
     * @return com.mszlu.blog.vo.Result
     **/
    Result hots(int limit);

    Result finAll();

    Result findAllDetail();

    Result findDetailById(Long id);
}
