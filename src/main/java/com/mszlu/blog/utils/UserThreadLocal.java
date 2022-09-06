package com.mszlu.blog.utils;

import com.mszlu.blog.pojo.SysUser;

/**
 * @author ginga
 * @version 1.0
 * @ClassName UserThreadLocal
 * @Date 6/9/2022 下午3:06
 */
public class UserThreadLocal {

    private UserThreadLocal() {}

    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser) {
        LOCAL.set(sysUser);
    }

    public static SysUser get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

}
