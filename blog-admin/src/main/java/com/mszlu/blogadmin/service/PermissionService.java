package com.mszlu.blogadmin.service;

import com.mszlu.blogadmin.pojo.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mszlu.blogadmin.vo.Result;
import com.mszlu.blogadmin.vo.params.PageParam;

/**
* @author 17400
* @description 针对表【ms_permission】的数据库操作Service
* @createDate 2022-09-11 19:55:40
*/
public interface PermissionService {

    Result listPermissions(PageParam pageParam);

    Result add(Permission permission);

    Result update(Permission permission);

    Result delete(Long id);
}
