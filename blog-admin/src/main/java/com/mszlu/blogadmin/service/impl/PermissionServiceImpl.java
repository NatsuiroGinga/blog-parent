package com.mszlu.blogadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mszlu.blogadmin.pojo.Permission;
import com.mszlu.blogadmin.service.PermissionService;
import com.mszlu.blogadmin.mapper.PermissionMapper;
import com.mszlu.blogadmin.vo.PageResult;
import com.mszlu.blogadmin.vo.Result;
import com.mszlu.blogadmin.vo.params.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 17400
* @description 针对表【ms_permission】的数据库操作Service实现
* @createDate 2022-09-11 19:55:40
*/
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Result listPermissions(PageParam pageParam) {

        final Page<Permission> permissionPage = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        final LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        final String queryString = pageParam.getQueryString();
        queryWrapper.eq(StringUtils.isNotBlank(queryString), Permission::getName, queryString);
        final Page<Permission> page = permissionMapper.selectPage(permissionPage, queryWrapper);
        final PageResult<Permission> pageResult = new PageResult<>();
        pageResult.setList(page.getRecords());
        pageResult.setTotal(page.getTotal());

        return Result.success(pageResult);
    }

    @Override
    public Result add(Permission permission) {
        permissionMapper.insert(permission);
        return Result.success(null);
    }

    @Override
    public Result update(Permission permission) {
        permissionMapper.updateById(permission);
        return Result.success(null);
    }

    @Override
    public Result delete(Long id) {
        permissionMapper.deleteById(id);
        return Result.success(null);
    }


}




