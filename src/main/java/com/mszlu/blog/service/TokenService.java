package com.mszlu.blog.service;

import com.mszlu.blog.pojo.SysUser;

/**
 * @author ginga
 * @version 1.0
 * @ClassName TokenService
 * @Date 5/9/2022 下午10:28
 */
public interface TokenService {

    /**
     * 检验token返回用户信息
     * @author ginga
     * @Date 5/9/2022 下午10:05
     * @param token 用户本地的token
     * @return com.mszlu.blog.pojo.SysUser
     **/
    SysUser checkToken(String token);

}
