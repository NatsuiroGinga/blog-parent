package com.mszlu.blog.controller;

import com.mszlu.blog.service.SSOService;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.RegisterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginga
 * @version 1.0
 * @ClassName RegisterController
 * @Date 6/9/2022 上午7:19
 */
@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private SSOService ssoService;

    @PostMapping
    public Result register(@RequestBody RegisterParam registerParam) {
        return ssoService.register(registerParam);
    }

}
