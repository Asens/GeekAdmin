package com.geekutil.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.mapper.UserMapper;
import com.geekutil.modules.sys.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Asens
 * create 2019-07-17 22:04
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
