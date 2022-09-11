package com.mszlu.blog.common.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

/**
 * @author ginga
 * @version 1.0
 * @ClassName CacheAspect
 * @Date 11/9/2022 下午3:51
 */
@Aspect // 切面 定义了通知和切点的关系
@Component
@Slf4j
public class CacheAspect {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(com.mszlu.blog.common.cache.Cache)")
    public void pt() {}

    @Around("pt()")
    public Object around(ProceedingJoinPoint point) {
        try {
            // 方法签名
            final Signature signature = point.getSignature();
            // 类名
            final String className = point.getClass().getSimpleName();
            // 方法名
            final String methodName = signature.getName();

            final Object[] args = point.getArgs();
            Class<?>[] parameterTypes = new Class[args.length];

            // 参数
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    params.append(JSON.toJSONString(args[i]));
                    parameterTypes[i] = args[i].getClass();
                } else {
                    parameterTypes[i] = null;
                }
            }

            if (StringUtils.isNotEmpty(params)) {
                params.replace(0, params.length(), DigestUtils.md5Hex(String.valueOf(params)));
            }

            final Class<?> declaringType = signature.getDeclaringType();
            final Method method = declaringType.getMethod(methodName, parameterTypes);
            // 获取cache注解
            final Cache cache = method.getAnnotation(Cache.class);
            final long expire = cache.expire();
            final String name = cache.name();
            // redis
            String redisKey = name + "::" + className + "::" + methodName + "::" + params;
            String redisValue = redisTemplate.opsForValue().get(redisKey);


        } catch (Exception e) {
            e.printStackTrace();;
        }

        return null;
    }
}
