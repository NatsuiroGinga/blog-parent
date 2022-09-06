package com.mszlu.blog.service;

import com.mszlu.blog.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mszlu.blog.vo.Result;
import org.apache.ibatis.annotations.Param;

/**
* @author 17400
* @description 针对表【ms_sys_user】的数据库操作Service
* @createDate 2022-07-03 23:48:21
*/
public interface SysUserService {

    /**
     * 根据作者id查询作者
     * @author ginga
     * @Date 4/9/2022 上午8:40
     * @param id 作者id
     * @return com.mszlu.blog.pojo.SysUser
     **/
    SysUser findUserById(@Param("id") Long id);

    /**
     * 根据账号和密码查询用户信息
     * @author ginga
     * @Date 5/9/2022 下午9:17
     * @param account 账号
     * @param password 密码
     * @return com.mszlu.blog.pojo.SysUser
     **/
    SysUser findUser(String account, String password);

    /**
     * 根据token查询用户信息
     * @author ginga
     * @Date 5/9/2022 下午9:57
     * @param token 用户本地的token
     * @return com.mszlu.blog.vo.Result
     **/
    Result findUserByToken(String token);

    SysUser findUserByAccount(String account);

    void save(SysUser user);
}
