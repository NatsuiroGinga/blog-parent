package com.mszlu.blogadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mszlu.blogadmin.pojo.Admin;
import com.mszlu.blogadmin.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ginga
 * @version 1.0
 * @ClassName AdminMapper
 * @Date 11/9/2022 下午9:11
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    List<Permission> findPermissionByAdminId(@Param("adminId") Long adminId);
}
