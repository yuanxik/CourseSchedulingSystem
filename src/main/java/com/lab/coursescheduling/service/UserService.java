package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.User;

public interface UserService {

    User getUser(String name);

    User getUserById(Integer id);

    User checkUser(String username);
}
