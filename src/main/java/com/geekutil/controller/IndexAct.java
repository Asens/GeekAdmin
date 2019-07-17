package com.geekutil.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geekutil.entity.User;
import com.geekutil.service.UserService;
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
        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername,"Asens"));
        return user;
    }
}
