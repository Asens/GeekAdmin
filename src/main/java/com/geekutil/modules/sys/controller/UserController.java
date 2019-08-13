package com.geekutil.modules.sys.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekutil.common.auth.Auth;
import com.geekutil.common.util.Result;
import com.geekutil.common.validate.ValidateUtils;
import com.geekutil.common.validate.group.AddGroup;
import com.geekutil.common.validate.group.UpdateGroup;
import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.entity.UserRole;
import com.geekutil.modules.sys.entity.dto.UserDTO;
import com.geekutil.modules.sys.service.RoleService;
import com.geekutil.modules.sys.service.UserRoleService;
import com.geekutil.modules.sys.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

import static com.geekutil.common.util.PageUtils.pageResult;
import static com.geekutil.common.validate.ValidateUtils.validator;
import static java.util.stream.Collectors.toSet;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Asens
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/api/sys/user")
@Log4j2
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleService roleService;

    /**
     * 用户列表
     */
    @Auth(value = "system.user.list")
    @GetMapping("/list")
    public Object list(@RequestParam(required = false , defaultValue = "1") Integer pageNo) {
        IPage<User> page = userService.lambdaQuery().page(new Page<>(pageNo, 10));
        return Result.success("result", pageResult(page));
    }

    /**
     * 用户编辑
     */
    @Auth(value = "system.user.edit")
    @GetMapping("/edit")
    public Object edit(@RequestParam Long id) {
        return Result.success("result", userService.getById(id));
    }

    /**
     * 保存用户
     */
    @Auth(value = {"system.user.save"})
    @PostMapping("/save")
    public Object save(UserDTO userDTO) {
        ComplexResult result = FluentValidator.checkAll(new Class<?>[]
                {userDTO.getId()==null?AddGroup.class:UpdateGroup.class})
                .failOver()
                .on(userDTO, new HibernateSupportedValidator<UserDTO>().setHiberanteValidator(validator()))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if(!result.isSuccess()){
            return Result.error(ValidateUtils.getErrorMessage(result.getErrors()));
        }
        userService.saveOrUpdateUser(userDTO);
        return Result.success();
    }

    /**
     * 授予角色页面,返回用户已有的角色用于展示
     */
    @GetMapping("/auth")
    public Object auth(@RequestParam Long id) {
        List<Role> roleList = roleService.list();
        List<UserRole> userRoleList = userRoleService.list(
                new LambdaQueryWrapper<UserRole>()
                        .eq(UserRole::getUserId,id));
        Set<Long> roleIdSet = userRoleList.stream()
                .map(UserRole::getRoleId).collect(toSet());
        JSONArray array = new JSONArray();
        for(Role role:roleList){
            JSONObject o = new JSONObject();
            o.put("name",role.getName());
            o.put("id",role.getId());
            o.put("checked",roleIdSet.contains(role.getId()));
            array.add(o);
        }
        return Result.success("result", array);
    }

    /**
     * 授予角色
     */
    @PostMapping("/doAuth")
    public Object doAuth(@RequestParam Long id,Long[] roles) {
        userService.doAuth(id,roles);
        return Result.success();
    }
}
