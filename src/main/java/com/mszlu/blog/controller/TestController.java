package com.mszlu.blog.controller;

import com.mszlu.blog.pojo.SysUser;
import com.mszlu.blog.utils.UserThreadLocal;
import com.mszlu.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginga
 * @version 1.0
 * @ClassName TestController
 * @Date 6/9/2022 上午11:52
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test() {
        final SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}
