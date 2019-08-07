package com.geekutil.modules.sys.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekutil.Const;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.geekutil.common.util.PageUtils.pageResult;
import static java.util.stream.Collectors.toList;

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
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    private final static String PAGE_BASIC_LAY_OUT = "BasicLayout";
    private final static String PAGE_ROUTE_VIEW = "RouteView";
    private final static String PAGE_VIEW = "PageView";

    @GetMapping("/list")
    public Object list(Integer pageNo) {
        if (pageNo == null) {
            pageNo = 1;
        }
        IPage<User> page = userService.lambdaQuery().page(new Page<>(pageNo, 10));
        return Result.success("result", pageResult(page));
    }

    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> paramMap) {
        String username = paramMap.get("username");
        String password = paramMap.get("password");
        log.info("username : [{}], password:[{}]", username, password);
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.error();
        }
        User user = userService.findByUsername(username);
        if (user != null && userService.checkPassword(user, password)) {
            String token = userService.createToken(user.getId());
            return Result.success("result", userVo(user, token));
        }
        return Result.error();
    }

    @GetMapping("/menus")
    public Object menus() {
        Long userId = FrontUtils.getCurrentUserId();
        log.info("info userId:[{}]", userId);
        List<Integer> roleList = roleService.getListByUser(userId);
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
        return Result.success("result", menus);
    }

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

    @GetMapping("/info")
    public Object info() {
        Long userId = FrontUtils.getCurrentUserId();
        log.info("info userId:[{}]", userId);
        User user = userService.getById(userId);
        if (user == null) {
            return Result.error();
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo, "password");
        List<Integer> roleList = roleService.getListByUser(userId);

        List<Permission> permissionList = permissionService.getListByRoleIds(roleList);
        JSONObject role = new JSONObject();
        role.put("permissions", permissionList.stream().map(t -> {
            PermissionVO permissionVO = new PermissionVO();
            permissionVO.setPermissionId(t.getCode());
            permissionVO.setPermissionName(t.getName());
            return permissionVO;
        }).collect(toList()));
        userVo.setRole(role);
        return Result.success("result", userVo);
    }

    private Object userVo(User user, String token) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo, "password");
        userVo.setToken(token);
        return userVo;
    }
}
