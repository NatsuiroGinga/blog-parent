package com.mszlu.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.mszlu.blog.pojo.SysUser;
import com.mszlu.blog.service.SSOService;
import com.mszlu.blog.service.SysUserService;
import com.mszlu.blog.utils.JWTUtils;
import com.mszlu.blog.vo.ErrorCode;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.LoginParam;
import com.mszlu.blog.vo.params.RegisterParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author ginga
 * @version 1.0
 * @ClassName LoginServiceImpl
 * @Date 5/9/2022 下午8:02
 */
@Service
@Transactional
public class SSOServiceImpl implements SSOService {

    @Autowired
    @Lazy
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String salt = "mszlu!@#";

    @Override
    public Result login(@NotNull LoginParam loginParam) {

        /*
          1. 检查参数是否合法
          2. 根据用户名和密码去user表中查询是否存在
          3. 如果不存在, 登陆失败
          4. 如果存在, 使用jwt生成token, 返回给前端
          5. token放入redis当中, redis token: user 信息, 设置过期时间(登录认证的时候, 先认证字符串是否合法, 去redis认证是否存在)
          */

        final String account = loginParam.getAccount();
        String password = loginParam.getPassword();

        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        password = DigestUtils.md5Hex(password + salt);

        SysUser sysUser = sysUserService.findUser(account, password);
        if (sysUser == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        final String token = JWTUtils.createToken(sysUser.getId());

        redisTemplate.opsForValue()
                .set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);

        return Result.success(token);
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }

    @Override
    public Result register(RegisterParam registerParam) {

        /*
        * 1. 判断参数是否合法
        * 2. 判断账户是否存在. 存在, 返回账户已被注册
        * 3. 账户不存在, 注册用户
        * 4. 生成token
        * 5. 存入redis并返回
        * 6. 加上事务, 中间任何过程出现问题, 回滚
        * */
        final String account = registerParam.getAccount();
        final String nickname = registerParam.getNickname();
        final String password = registerParam.getPassword();
        if (StringUtils.isBlank(account)
        || StringUtils.isBlank(password)
        || StringUtils.isBlank(nickname)) {

            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        SysUser sysUser = sysUserService.findUserByAccount(account);
        if (sysUser != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }

        final SysUser user = new SysUser();
        user.setNickname(nickname);
        user.setAccount(account);
        user.setPassword(DigestUtils.md5Hex(password + salt));
        user.setAdmin(1);
        user.setDeleted(0);
        user.setAvatar("/static/img/logo.b3a48c0.png");
        user.setEmail("");
        user.setCreateDate(System.currentTimeMillis());
        user.setSalt("");
        user.setLastLogin(System.currentTimeMillis());
        user.setStatus("");
        sysUserService.save(user);

        String token = JWTUtils.createToken(user.getId());
        redisTemplate.opsForValue()
                .set("TOKEN_" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);

        return Result.success(token);
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("100mszlu!@#"));
    }

}
