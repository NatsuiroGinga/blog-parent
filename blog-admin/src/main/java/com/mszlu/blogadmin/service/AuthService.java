package com.mszlu.blogadmin.service;

import com.mszlu.blogadmin.pojo.Admin;
import com.mszlu.blogadmin.pojo.Permission;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author ginga
 * @version 1.0
 * @ClassName AuthService
 * @Date 11/9/2022 下午9:18
 */
@Service
public class AuthService {

    @Autowired
    private AdminService adminService;

    public boolean auth(@NotNull HttpServletRequest request, Authentication authentication) {
        // 请求路径
        String requestURI = request.getRequestURI();
        final Object principal = authentication.getPrincipal();
        // 为空 或者 为匿名用户
        if (principal == null || "anonymousUser".equals(principal)) {
            // 未登录
            return false;
        }
        UserDetails userDetails = (UserDetails) principal;
        final String username = userDetails.getUsername();
        final Admin admin = adminService.findAdminByUserName(username);

        if (admin == null) {
            return false;
        }
        // 超级管理员, 直接放行
        if (admin.getId() == 1) {
            return true;
        }

        final Long adminId = admin.getId();
        List<Permission> permissionList = adminService.findPermissionByAdminId(adminId);
        requestURI = StringUtils.split(requestURI, '?')[0];

        String finalRequestURI = requestURI;
        return permissionList.stream()
                             .anyMatch(permission -> finalRequestURI.equals(permission.getPath()));
    }

}
