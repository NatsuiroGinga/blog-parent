package com.mszlu.blog.controller;

import com.mszlu.blog.service.SSOService;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginga
 * @version 1.0
 * @ClassName LoginController
 * @Date 5/9/2022 下午7:59
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private SSOService SSOService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam) {
        // 登录 验证用户 访问用户表
        return SSOService.login(loginParam);
    }

}
