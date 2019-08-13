package com.geekutil.common.auth;

import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.service.PermissionService;
import com.geekutil.modules.sys.service.RoleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Asens
 * create 2019-08-11 9:54
 **/
@Component
public class AuthService {
    private Map<Long, Set<String>> userRoleMap = new HashMap<>();
    private Map<String, Set<String>> rolePermissionMap = new HashMap<>();

    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    public boolean hasPermission(Long userId, String[] permissions, String[] roles) {
        for(String role : roles){
            if(!roles(userId).contains(role)){
                return false;
            }
        }

        for(String permission : permissions){
            Set<String> set = permissions(userId);
            if(!set.contains(permission)){
                return false;
            }
        }
        return true;
    }

    private Set<String> permissions(Long userId) {
        Set<String> roles = roles(userId);
        Set<String> permissions = new HashSet<>();
        for(String role : roles){
            if(!rolePermissionMap.containsKey(role)){
                Role r = roleService.lambdaQuery().eq(Role::getCode,role).one();
                List<Permission> list = permissionService.getListByRole(r.getId());
                rolePermissionMap.put(role,list.stream().map(Permission::getCode)
                        .collect(Collectors.toSet()));
            }
            permissions.addAll(rolePermissionMap.get(role));
        }
        return permissions;
    }

    private Set<String> roles(Long userId){
        if(!userRoleMap.containsKey(userId)){
            List<Role> roleList = roleService.getRoleListByUser(userId);
            Set<String> set = roleList.stream().map(Role::getCode)
                    .collect(Collectors.toSet());
            userRoleMap.put(userId,set);
            return set;
        }
        return userRoleMap.get(userId);
    }

    public void removeUser(Long userId){
        userRoleMap.remove(userId);
    }

    public void removeRole(String roleCode){
        rolePermissionMap.remove(roleCode);
    }
}
