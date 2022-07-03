package com.lab.coursescheduling.web.controller;/*
    @auther
    @create ---
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExerController {

    @GetMapping("/exer/toEx")
    public String toEx(){
        return "ex";
    }
}
