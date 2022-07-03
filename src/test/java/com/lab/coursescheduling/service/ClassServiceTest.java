package com.lab.coursescheduling.service;

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.po.Class;
import com.lab.coursescheduling.bean.vo.ClassVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.ClassMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/
@SpringBootTest(classes = CourseSchedulingApplication.class)
class ClassServiceTest {
@Autowired
private ClassService classService;


    @Test
    void findAll() {
        ServerResponse<PageInfo<ClassVo>> all = classService.findAll(1, 5);
        for (ClassVo c:all.getData().getList()){
            System.out.println(c);
        }
    }

    @Test
    void find(){
        ServerResponse<ClassVo> serverResponse = classService.find(2);
        System.out.println(serverResponse.getData());
    }
}