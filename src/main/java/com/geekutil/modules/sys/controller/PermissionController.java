package com.geekutil.modules.sys.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.geekutil.Const;
import com.geekutil.common.util.Result;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
@RequestMapping("/api/sys/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @GetMapping("/menuTree")
    public Object menuTree(){
        List<Permission> list = permissionService.list();
        List<Permission> rootList = list.stream().filter(t->StringUtils.isBlank(t.getParentCode()))
                .collect(toList());
        for(Permission permission:rootList){
            permission.setKey(permission.getId());
            if(permissionService.hasChild(permission.getCode(),list)){
                permissionService.addChildren(permission,list);
            }
        }
        return Result.success("result",rootList);
    }

    /**
     * 找出有子节点且子节点全是功能按钮的
     * 要依次按照顺序，从最顶级开始
     *
     * 有子菜单不允许创建子按钮
     * 有子按钮不允许创建子菜单
     *
     * 有的从来都不是菜单本身的权限，而是操作数据的权限
     * 只是我可以不让你看见而已
     *
     * 一个菜单本身的权限基本等同于查询的权限
     */
    @GetMapping("/menuList")
    public Object menuList(){
        List<Permission> list = permissionService.list();
        List<Permission> baseMenu = permissionService.baseMenu(list);
        JSONArray result = new JSONArray();
        for(Permission permission:baseMenu){
            JSONObject o = new JSONObject();
            o.put("id",permission.getId());
            o.put("name",permission.getName());
            o.put("status",permission.getStatus());
            o.put("deleted",0);
            List<Permission> children = permissionService.getChildren(permission,list);
            o.put("actions",children.stream().map(Permission::getCode).collect(toList()));
            o.put("actionData",children.stream().map(t->{
                JSONObject j = new JSONObject();
                j.put("action",t.getCode());
                j.put("defaultCheck",false);
                j.put("describe",t.getName());
                return j;
            }).collect(toList()));
            result.add(o);
        }

        return Result.success("result",result);
    }

    @PostMapping("/save")
    public Object save(Permission permission){
        permissionService.saveOrUpdate(permission);
        return Result.success();
    }

    @GetMapping("/info")
    public Object info(String code){
        Permission permission = permissionService.lambdaQuery()
                .eq(Permission::getCode,code).one();
        return Result.success("result",permission);
    }

    @GetMapping("/delete")
    public Object delete(Long id){
        List<Permission> list = permissionService.list();
        List<Long> toDelete = new ArrayList<>();
        Permission permission = list.stream().filter(t->Objects.equals(id,t.getId()))
                .findFirst().get();
        if(!permissionService.hasChild(permission.getCode(),list)){
            permissionService.removeById(permission.getId());
            return Result.success();
        }

        toDelete.add(id);
        permissionService.addToDelete(permission,list,toDelete);
        permissionService.removeByIds(toDelete);
        return Result.success("result",permission);
    }

}
