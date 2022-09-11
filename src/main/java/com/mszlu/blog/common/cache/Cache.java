package com.mszlu.blog.common.cache;

import java.lang.annotation.*;

/**
 * @author ginga
 * @version 1.0
 * @ClassName Cache
 * @Date 11/9/2022 下午3:48
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    // 缓存过期时间(ms)
    long expire() default 60 * 1000;

    // 缓存标识 key
    String name() default "";
}
