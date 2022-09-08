package com.mszlu.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.mszlu.blog.pojo.SysUser;
import com.mszlu.blog.service.TokenService;
import com.mszlu.blog.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ginga
 * @version 1.0
 * @ClassName TokenServiceImpl
 * @Date 5/9/2022 下午10:28
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public SysUser checkToken(String token) {

        if (StringUtils.isBlank(token)) {
            return null;
        }

        final Map<String, Object> map = JWTUtils.checkToken(token);
        if (map == null) {
            return null;
        }
        final String userJson = redisTemplate.opsForValue()
                .get("TOKEN_" + token);

        if (StringUtils.isBlank(userJson)) {
            return null;
        }

        return JSON.parseObject(userJson, SysUser.class);
    }
}
