package com.test.service;

import com.test.model.User;

/**
 * @author herixin
 * @create 2022-12-15 10:14
 */
public interface IUserService {
    public String userRegister(User user);
    User login(User user);

}
