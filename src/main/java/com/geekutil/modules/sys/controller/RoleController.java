package com.geekutil.modules.sys.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geekutil.Const;
import com.geekutil.common.util.Result;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.service.PermissionService;
import com.geekutil.modules.sys.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Asens
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/api/sys/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    @GetMapping("/list")
    public Object menuTree(){
        List<Role> list = roleService.list();
        JSONArray result = new JSONArray();
        for(Role role:list){
            JSONObject o = new JSONObject();
            List<Permission> permissionList =  permissionService.getListByRole(role.getId());
            List<Permission> baseMenu = permissionService.baseMenu(permissionList);
            o.put("id",role.getId());
            o.put("name",role.getName());
            o.put("status",role.getStatus());
            o.put("deleted",0);
            JSONArray permissions = new JSONArray();
            for(Permission permission:baseMenu){
                JSONObject op = new JSONObject();
                op.put("permissionId",permission.getCode());
                op.put("permissionName",permission.getName());
                op.put("status",permission.getStatus());
                op.put("deleted",0);
                List<Permission> children = permissionService.getChildren(permission,permissionList);
                op.put("actionData",children.stream().map(t->{
                    JSONObject j = new JSONObject();
                    j.put("action",t.getCode());
                    j.put("describe",t.getName());
                    j.put("defaultCheck",false);
                    return j;
                }).collect(toList()));
                op.put("actions",children.stream().map(Permission::getCode).collect(toList()));
                permissions.add(op);
            }
            o.put("permissions",permissions);
            result.add(o);
        }
        return Result.success("result",result);
    }
}
