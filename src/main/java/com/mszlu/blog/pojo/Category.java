package com.mszlu.blog.pojo;

import lombok.Data;

/**
 * @author ginga
 * @version 1.0
 * @ClassName Category
 * @Date 6/9/2022 下午5:23
 */
@Data
public class Category {

    private Long id;

    private String avatar;

    private String categoryName;

    private String description;

}
