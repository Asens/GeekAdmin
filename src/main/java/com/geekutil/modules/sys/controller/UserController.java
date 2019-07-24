package com.geekutil.modules.sys.controller;


import com.alibaba.fastjson.JSONObject;
import com.geekutil.common.util.FrontUtils;
import com.geekutil.common.util.Result;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.entity.vo.PermissionVO;
import com.geekutil.modules.sys.entity.vo.UserVo;
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
 * <p>
 *  前端控制器
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
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @PostMapping("/login")
    public Object login(@RequestBody Map<String,String> paramMap){
        String username = paramMap.get("username");
        String password = paramMap.get("password");
        log.info("username : [{}], password:[{}]",username,password);
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return Result.error();
        }
        User user = userService.findByUsername(username);
        if(user!=null && userService.checkPassword(user,password)){
            String token = userService.createToken(user.getId());
            return Result.success("result",userVo(user,token));
        }
        return Result.error();
    }

    @GetMapping("/info")
    public Object info(){
        Long userId = FrontUtils.getCurrentUserId();
        log.info("info userId:[{}]",userId);
        User user = userService.getById(userId);
        if(user==null){
            return Result.error();
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo,"password");
        List<Integer> roleList = roleService.getListByUser(userId);

        List<Permission> permissionList =  permissionService.getListByRoleIds(roleList);
        JSONObject role = new JSONObject();
        role.put("permissions",permissionList.stream().map(t->{
            PermissionVO permissionVO = new PermissionVO();
            permissionVO.setPermissionId(t.getCode());
            permissionVO.setPermissionName(t.getName());
            return permissionVO;
        }).collect(toList()));
        userVo.setRole(role);
        return Result.success("result",userVo);
    }

    private Object userVo(User user, String token) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo,"password");
        userVo.setToken(token);
        return userVo;
    }
}
