package com.mszlu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mszlu.blog.mapper.SysUserMapper;
import com.mszlu.blog.pojo.SysUser;
import com.mszlu.blog.service.SysUserService;
import com.mszlu.blog.service.TokenService;
import com.mszlu.blog.vo.ErrorCode;
import com.mszlu.blog.vo.LoginUserVo;
import com.mszlu.blog.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ginga
 * @version 1.0
 * @ClassName SysUserServiceImpl
 * @Date 4/9/2022 上午8:38
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public SysUser findUserById(Long id) {
        final SysUser sysUser = sysUserMapper.selectById(id);

        if (sysUser == null) {
            final SysUser user = new SysUser();
            user.setNickname("mszl");
        }

        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String password) {

        final LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account)
                .eq(SysUser::getPassword, password);
        queryWrapper.select(SysUser::getAccount, SysUser::getId, SysUser::getAvatar, SysUser::getNickname)
                .last("limit 1");

        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public Result findUserByToken(String token) {

        /**
         * 1. 合法性校验
         *    是否为空, 解析是否成功, redis是否存在
         * 2. 如果校验失败, 返回错
         * 3. 如果成功, 返回对应结果, LoginUserVo
         * */
        SysUser sysUser = tokenService.checkToken(token);

        if (sysUser == null) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }

        final LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(sysUser, loginUserVo);
//        loginUserVo.setId(sysUser.getId());
//        loginUserVo.setAvatar(sysUser.getAvatar());
//        loginUserVo.setNickname(sysUser.getNickname());
//        loginUserVo.setAccount(sysUser.getAccount());

        return Result.success(loginUserVo);
    }
}
