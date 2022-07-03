package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.User;

import com.lab.coursescheduling.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUser(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User checkUser(String username) {
        return userMapper.checkUser(username);
    }
}
