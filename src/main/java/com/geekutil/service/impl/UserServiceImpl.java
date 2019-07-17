package com.geekutil.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekutil.dao.UserDao;
import com.geekutil.entity.User;
import com.geekutil.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Asens
 * create 2019-07-17 22:04
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
