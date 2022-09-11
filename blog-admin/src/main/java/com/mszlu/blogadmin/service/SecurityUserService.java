package com.mszlu.blogadmin.service;

import com.mszlu.blogadmin.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author ginga
 * @version 1.0
 * @ClassName SecurityUserService
 * @Date 11/9/2022 下午9:09
 */
@Component
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*
        * 登陆的时候， 会把UserName传递到这里
        * 通过username查询用户表， 如果admin存在， 将密码告诉spring security
        * 如果不存在， 返回null， 认证失败
        * */
        final Admin admin = adminService.findAdminByUserName(username);
        if (admin == null) {
            return null;
        }

        return new User(username, admin.getPassword(), new ArrayList<>());
    }
}
