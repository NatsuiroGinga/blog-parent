package com.mszlu.blog.handler;

import com.alibaba.fastjson.JSON;
import com.mszlu.blog.pojo.SysUser;
import com.mszlu.blog.service.SSOService;
import com.mszlu.blog.service.TokenService;
import com.mszlu.blog.utils.UserThreadLocal;
import com.mszlu.blog.vo.ErrorCode;
import com.mszlu.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author ginga
 * @version 1.0
 * @ClassName LoginInterceptor
 * @Date 6/9/2022 上午11:27
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {

        /*
        * 1. 需要判断请求的接口路径是否为 HandlerMethod (controller方法)
        * 2. 判断token是否为空, 如果为空, 未登录
        * 3. 如果token不为空, 进行登陆验证 loginService-checkToken
        * 4. 如果认证成功, 执行
        * */

        if (!(handler instanceof HandlerMethod)) {
            // springboot程序访问静态资源默认去classpath下的static目录去查询
            return true;
        }

        final String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            final Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter()
                    .print(JSON.toJSONString(result));

            return false;
        }

        final SysUser sysUser = tokenService.checkToken(token);
        if (sysUser == null) {
            final Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter()
                    .print(JSON.toJSONString(result));

            return false;
        }

        // 登录验证成功
        UserThreadLocal.put(sysUser);

        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                @NotNull Object handler, Exception ex) throws Exception {
        // 如果不删除, 会有内存泄露的风险
        UserThreadLocal.remove();
    }
}
