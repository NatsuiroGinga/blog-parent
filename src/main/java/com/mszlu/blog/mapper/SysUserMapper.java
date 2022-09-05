package com.mszlu.blog.mapper;

import com.mszlu.blog.pojo.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17400
* @description 针对表【ms_sys_user】的数据库操作Mapper
* @createDate 2022-07-03 23:48:21
* @Entity com.mszlu.blog.pojo.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}




