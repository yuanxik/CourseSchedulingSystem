package com.lab.coursescheduling.web.admin;

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.po.ClassTask;
import com.lab.coursescheduling.service.ClassTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/
@SpringBootTest(classes = CourseSchedulingApplication.class)
class ClassTaskControllerTest {

    @Autowired
    private ClassTaskService classTaskService;
    @Test
    void listClassTasks() {

    }

    @Test
    void deleteClassTask() {
    }
}