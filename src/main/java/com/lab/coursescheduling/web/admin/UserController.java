package com.lab.coursescheduling.web.admin;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("toinfo")
    public String toAdmininfo(){
        return "administrator/stu_info_admin";
    }

    @GetMapping("toschedule")
    public String toAdminschedule(){
        return "administrator/stu_info_admin";
    }

    @GetMapping("toorder")
    public String toAdminorder(){
        return "administrator/stu_info_admin";
    }





}
