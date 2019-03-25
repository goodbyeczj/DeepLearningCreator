package com.rkb.service;

import com.rkb.pojo.User;

public interface UserService {
    //用户注册
    void regist(User user);
    //用户登录
    User login(String name, String password);
    User findUserById(Long id);
    Boolean assertCore(String core);
    Boolean forgotPw(User user);
    User findUserByName(String name);
}