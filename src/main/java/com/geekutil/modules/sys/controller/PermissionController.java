package com.geekutil.modules.sys.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.geekutil.Const;
import com.geekutil.common.util.Result;
import com.geekutil.common.validate.LengthValidator;
import com.geekutil.common.validate.NotEmptyValidator;
import com.geekutil.common.validate.ValidateUtils;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.geekutil.Const.DATABASE_INTEGER_NO;
import static java.util.stream.Collectors.toList;

/**
 * 权限
 * 权限包含菜单和功能按钮(对应的接口)
 * 用户有菜单权限,可以看到对应的菜单
 * 用户有功能按钮权限,用户可以看见对应的功能按钮,点击按钮有对应的接口权限
 * 因此部分接口和函数会以menu命名
 *
 * @author Asens
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/api/sys/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    /**
     * 权限树形表格
     */
    @GetMapping("/menuTree")
    public Object menuTree(){
        List<Permission> result = permissionService.menuTree();
        return Result.success().data(result);
    }

    /**
     * 角色授权时的权限列表,是所有有视图的菜单的集合
     *
     * 找出有子节点且子节点全是功能按钮的
     * 要依次按照顺序，从最顶级开始
     *
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
            o.put("code",permission.getCode());
            o.put("status",permission.getStatus());
            o.put("deleted",DATABASE_INTEGER_NO);
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

        return Result.success().data(result);
    }

    /**
     * 保存权限
     * 有子菜单不允许创建子按钮
     * 有子按钮不允许创建子菜单
     */
    @PostMapping("/save")
    public Object save(Permission permission){
        ComplexResult result = FluentValidator.checkAll()
                .on(permission.getName(), new NotEmptyValidator("菜单名称"))
                .on(permission.getName(), new LengthValidator(1,20,"菜单名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if(!result.isSuccess()){
            return Result.error(ValidateUtils.getErrorMessage(result.getErrors()));
        }
        permissionService.saveOrUpdate(permission);
        return Result.success();
    }

    /**
     * 权限基本信息
     */
    @GetMapping("/info")
    public Object info(@RequestParam("code") String code){
        Permission permission = permissionService.lambdaQuery()
                .eq(Permission::getCode,code).one();
        return Result.success().data(permission);
    }

    /**
     * 删除权限
     * 删除权限要删除其所有的子权限
     */
    @GetMapping("/delete")
    public Object delete(@RequestParam("id") Long id){
        permissionService.delete(id);
        return Result.success("data");
    }

}
