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
import com.geekutil.modules.sys.entity.dto.UserDTO;
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

    /**
     * 用户列表
     */
    @GetMapping("/list")
    public Object list(Integer pageNo) {
        if (pageNo == null) {
            pageNo = 1;
        }
        IPage<User> page = userService.lambdaQuery().page(new Page<>(pageNo, 10));
        return Result.success("result", pageResult(page));
    }

    @GetMapping("/edit")
    public Object edit(Long id) {
        return Result.success("result", userService.getById(id));
    }

    @PostMapping("/save")
    public Object doAuth(UserDTO userDTO) {
        userService.saveOrUpdateUser(userDTO);
        return Result.success();
    }

    @GetMapping("/auth")
    public Object auth(Long id) {
        return Result.success("result", userService.getById(id));
    }

    @PostMapping("/doAuth")
    public Object doAuth(Long id) {
        return Result.success("result", userService.getById(id));
    }
}
