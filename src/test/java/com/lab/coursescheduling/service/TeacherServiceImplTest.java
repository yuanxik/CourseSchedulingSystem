package com.lab.coursescheduling.service;

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.po.Teacher;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/
@SpringBootTest(classes = CourseSchedulingApplication.class)
class TeacherServiceImplTest {

    @Autowired
    private TeacherService teacherService;
    @Test
    void findAll() {
        ServerResponse<PageInfo<Teacher>> all = teacherService.findAll(1, 5);
        System.out.println(all.getStatus());
        PageInfo<Teacher> data = all.getData();
        System.out.println(data.getTotal());
        for (Teacher t: data.getList()){
            System.out.println(t);
        }
    }
}