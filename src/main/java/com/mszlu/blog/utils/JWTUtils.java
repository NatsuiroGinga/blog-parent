package com.mszlu.blog.utils;

import io.jsonwebtoken.*;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ginga
 * @version 1.0
 * @ClassName JWTUtils
 * @Date 5/9/2022 上午7:31
 */
public class JWTUtils {

    private static final String jwtToken = "123456Mszlu!@#$$";

    public static String createToken(Long userId) {
        final HashMap<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);

        final JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken) // 签发算法, 密钥为jwtToken
                .setClaims(claims) // body数据, 要唯一, 自行设置
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));// 一天的有效时间

        return jwtBuilder.compact();
    }

    @Nullable
    public static Map<String, Object> checkToken(String token) {
        try {
            final Jwt parse = Jwts.parser()
                    .setSigningKey(jwtToken)
                    .parse(token);

            return (Map<String, Object>) parse.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

        final String token = JWTUtils.createToken(100L);
        System.out.println(token);

        final Map<String, Object> map = JWTUtils.checkToken(token);
        System.out.println(Objects.requireNonNull(map).get("userId"));

    }

}
