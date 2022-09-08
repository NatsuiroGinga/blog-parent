package com.mszlu.blog.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author ginga
 * @version 1.0
 * @ClassName HttpContextUtils
 * @Date 8/9/2022 下午8:28
 */
public class HttpContextUtils {
    @NotNull
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
