package com.geekutil.modules.sys.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geekutil.Const;
import com.geekutil.common.util.FrontUtils;
import com.geekutil.common.util.Result;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.entity.dto.UserDTO;
import com.geekutil.modules.sys.service.PermissionService;
import com.geekutil.modules.sys.service.RoleService;
import com.geekutil.modules.sys.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * 登录,系统权限菜单
 * @author Asens
 * create 2019-08-08 4:09
 **/
@RestController
@RequestMapping("/api/sys/auth")
@Log4j2
public class AuthController {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    private final static String PAGE_BASIC_LAY_OUT = "BasicLayout";
    private final static String PAGE_ROUTE_VIEW = "RouteView";

    /**
     * 系统路由菜单,配合Vue动态路由
     */
    @GetMapping("/menus")
    public Object menus() {
        Long userId = FrontUtils.getCurrentUserId();
        log.info("info userId:[{}]", userId);
        List<Long> roleList = roleService.getListByUser(userId);
        List<Permission> permissionList = permissionService.getListByRoleIds(roleList);
        JSONObject index = new JSONObject();

        index.put("title", "首页");
        index.put("name", "首页");
        index.put("key", "");
        index.put("component", PAGE_BASIC_LAY_OUT);
        index.put("redirect", "/dashboard/workplace");
        List<Permission> rootList = permissionList.stream().filter(t -> StringUtils.isBlank(t.getParentCode()))
                .collect(toList());
        index.put("children", menus(rootList,permissionList));
        JSONArray menus = new JSONArray();
        menus.add(index);
        return Result.success().data(menus);
    }

    /**
     * 递归构建路由菜单
     */
    private Object menus(List<Permission> list,List<Permission> allList) {
        JSONArray array = new JSONArray();
        for (Permission permission : list) {
            JSONObject object = new JSONObject();
            if (Objects.equals(permission.getIsMenu(), Const.DATABASE_INTEGER_NO) &&
                    StringUtils.isBlank(permission.getRealPath())) {
                continue;
            }
            object.put("name", permission.getName());
            object.put("key", permission.getCode());
            object.put("isMenu", permission.getIsMenu());
            object.put("realPath", permission.getRealPath());
            object.put("icon", permission.getIcon());
            object.put("component", permission.getComponent());
            if(StringUtils.isBlank(permission.getComponent())){
                object.put("component", PAGE_ROUTE_VIEW);
            }
            if (permissionService.hasChild(permission.getCode(), allList)) {
                object.put("children", menus(permissionService.getChildren(permission,allList),allList));
            }
            array.add(object);
        }
        return array;
    }

    /**
     * 用户权限信息,包括用户基本信息,角色以及权限
     */
    @GetMapping("/info")
    public Object info() {
        Long userId = FrontUtils.getCurrentUserId();
        log.info("info userId:[{}]", userId);
        User user = userService.getById(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO, "password");
        List<Long> roleList = roleService.getListByUser(userId);

        List<Permission> permissionList = permissionService.getListByRoleIds(roleList);
        JSONObject role = new JSONObject();
        role.put("permissions", permissionList.stream().map(t -> {
            JSONObject o = new JSONObject();
            o.put("permissionId",t.getCode());
            o.put("permissionName",t.getName());
            return o;
        }).collect(toList()));
        userDTO.setRole(role);
        return Result.success().data(userDTO);
    }
}
