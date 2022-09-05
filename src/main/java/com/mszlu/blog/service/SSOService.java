package com.mszlu.blog.service;

import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.LoginParam;

/**
 * 登录服务
 * @author ginga
 * @version 1.0
 * @ClassName LoginService
 * @Date 5/9/2022 下午8:02
 */
public interface SSOService {
    /**
     * 用户登录
     * @author ginga
     * @Date 5/9/2022 下午9:40
     * @param loginParam 登陆参数
     * @return com.mszlu.blog.vo.Result
     **/
    Result login(LoginParam loginParam);

    /**
     * 登出
     * @author ginga
     * @Date 5/9/2022 下午10:51
     * @param token 令牌
     * @return com.mszlu.blog.vo.Result
     **/
    Result logout(String token);
}
