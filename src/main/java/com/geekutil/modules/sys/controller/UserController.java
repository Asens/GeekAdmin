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
import com.geekutil.Const;
import com.geekutil.common.auth.Auth;
import com.geekutil.common.util.DateUtils;
import com.geekutil.common.util.FrontUtils;
import com.geekutil.common.util.Result;
import com.geekutil.common.validate.UniqueUsernameValidator;
import com.geekutil.common.validate.ValidateUtils;
import com.geekutil.common.validate.group.AddGroup;
import com.geekutil.common.validate.group.UpdateGroup;
import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.entity.ScheduleJob;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.entity.UserRole;
import com.geekutil.modules.sys.entity.dto.UserDTO;
import com.geekutil.modules.sys.service.RoleService;
import com.geekutil.modules.sys.service.UserRoleService;
import com.geekutil.modules.sys.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

import static com.geekutil.common.util.HttpUtils.pageSize;
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
    public Object list(@RequestParam(required = false , defaultValue = "1") Integer pageNo,
                       String username,String name,String status,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") Date registerTimeStart,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") Date registerTimeEnd) {
        IPage<User> page = userService.lambdaQuery()
                .eq(status!=null, User::getStatus,status)
                .like(StringUtils.isNotBlank(name),User::getName,name)
                .like(StringUtils.isNotBlank(username),User::getUsername,username)
                .lt(registerTimeEnd!=null,User::getCreateDate, registerTimeEnd)
                .gt(registerTimeStart!=null,User::getCreateDate,registerTimeStart)
                .page(new Page<>(pageNo, pageSize()));
        return Result.success().data(pageResult(page));
    }

    /**
     * 用户编辑
     */
    @Auth(value = "system.user.edit")
    @GetMapping("/edit")
    public Object edit(@RequestParam Long id) {
        return Result.success().data(userService.getById(id));
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
                .putAttribute2Context("userService", userService)
                .on(userDTO.getUsername(),new UniqueUsernameValidator())
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
        return Result.success().data(array);
    }

    /**
     * 授予角色
     */
    @PostMapping("/doAuth")
    public Object doAuth(@RequestParam Long id,Long[] roles) {
        userService.doAuth(id,roles);
        return Result.success();
    }

    @GetMapping("/checkPassword")
    public Object checkPassword(@RequestParam String password) {
        Long userId = FrontUtils.getCurrentUserId();
        User user = userService.getById(userId);
        if(!Objects.equals(DigestUtils.md5DigestAsHex((password +
                Const.USER_SALT).getBytes()),user.getPassword())){
            return Result.error("原密码错误");
        }
        return Result.success();
    }
    /**
     * 修改密码
     */
    @PostMapping("/changePassword")
    public Object changePassword(@RequestParam String oldPassword,@RequestParam String password) {
        Long userId = FrontUtils.getCurrentUserId();
        User user = userService.getById(userId);
        if(!Objects.equals(DigestUtils.md5DigestAsHex((oldPassword +
                Const.USER_SALT).getBytes()),user.getPassword())){
            return Result.error("原密码错误");
        }
        user.setPassword(DigestUtils.md5DigestAsHex((password +
                Const.USER_SALT).getBytes()));
        userService.updateById(user);
        return Result.success();
    }

}
