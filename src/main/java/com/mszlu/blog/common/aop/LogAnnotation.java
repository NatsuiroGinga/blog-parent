package com.mszlu.blog.common.aop;

import java.lang.annotation.*;

/**
 * @author ginga
 * @version 1.0
 * @ClassName LogAnnotation
 * @Date 8/9/2022 下午8:13
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";

    String operator() default "";
}
