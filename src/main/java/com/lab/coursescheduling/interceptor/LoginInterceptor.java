package com.lab.coursescheduling.interceptor;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.Teacher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute("user")==null){
            Teacher teacher =(Teacher) request.getSession().getAttribute("teacher");
            if (teacher==null||teacher.getRole().equals("1"))  response.sendRedirect("/");
        }
        return true;
    }
}
