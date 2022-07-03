package com.lab.coursescheduling.mapper;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User getUserByName(String name);

    User getUserById(Integer id);

    User checkUser(String username);
}
