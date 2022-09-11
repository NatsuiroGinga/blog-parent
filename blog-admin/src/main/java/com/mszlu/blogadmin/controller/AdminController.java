package com.mszlu.blogadmin.controller;

import com.mszlu.blogadmin.pojo.Permission;
import com.mszlu.blogadmin.service.PermissionService;
import com.mszlu.blogadmin.vo.params.PageParam;
import com.mszlu.blogadmin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ginga
 * @version 1.0
 * @ClassName AdminController
 * @Date 11/9/2022 下午7:47
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("permission/permissionList")
    public Result listPermissions(@RequestBody PageParam pageParam) {
        return permissionService.listPermissions(pageParam);
    }

    @PostMapping("permission/add")
    public Result add(@RequestBody Permission permission){
        return permissionService.add(permission);
    }

    @PostMapping("permission/update")
    public Result update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }

}
