package com.geekutil.modules.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geekutil.common.util.Result;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Asens
 * create 2019-07-17 21:46
 **/

@RestController
public class IndexAct {
    @Resource
    private UserService userService;

    @GetMapping("/saveUser")
    public Object saveUser(){
        User user = new User();
        user.setUsername("Asens");
        userService.save(user);
        return "success";
    }

    @GetMapping("/")
    public Object index(){
        return Result.success();
    }
}
