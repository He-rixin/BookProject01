package com.test.service.impl;

import com.test.mapper.UserMapper;
import com.test.model.User;
import com.test.service.IUserService;
import com.test.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author herixin
 * @create 2022-12-15 10:17
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String userRegister(User user) {
        if (user == null || user.getUsername() == null || user.getUsername().trim().equals("") || user.getPassword() == null || user.getPassword().trim().equals("")) {
            return "输入有误";
        }
        User login = userMapper.login(user);
        if (login != null) {
            return "用户名已存在";
        } else {


            //获得加密的盐
            String salt = PasswordHelper.createSalt();
            String password = PasswordHelper.createCredentials(user.getPassword(), salt);
            user.setPassword(password);
            user.setSalt(salt);
            int i = userMapper.insert(user);
            if (i <= 0) {
                return "注册失败";
            } else {

                User user1 = userMapper.login(user);
                System.out.println("user1 = " + user1);
                int i1 = userMapper.insertRole(user1.getUserid(), 2);
                System.out.println("i1 = " + i1);
            }
            return "注册成功";
        }
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

}
