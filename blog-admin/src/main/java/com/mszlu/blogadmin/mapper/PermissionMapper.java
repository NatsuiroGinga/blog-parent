package com.mszlu.blogadmin.mapper;

import com.mszlu.blogadmin.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17400
* @description 针对表【ms_permission】的数据库操作Mapper
* @createDate 2022-09-11 19:55:40
* @Entity com.mszlu.blogadmin.pojo.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}




