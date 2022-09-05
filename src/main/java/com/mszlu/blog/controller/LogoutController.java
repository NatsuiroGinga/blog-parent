package com.mszlu.blog.controller;

import com.mszlu.blog.service.SSOService;
import com.mszlu.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginga
 * @version 1.0
 * @ClassName LogoutController
 * @Date 5/9/2022 下午10:50
 */
@RestController
@RequestMapping("logout")
public class LogoutController {

    @Autowired
    private SSOService SSOService;

    @GetMapping
    public Result logout(@RequestHeader("Authorization") String token) {
        return SSOService.logout(token);
    }

}
