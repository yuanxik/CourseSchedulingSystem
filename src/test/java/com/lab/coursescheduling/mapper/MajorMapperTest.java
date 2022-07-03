package com.lab.coursescheduling.mapper;

import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.po.Major;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/
@SpringBootTest(classes = CourseSchedulingApplication.class)
class MajorMapperTest {
    @Autowired
    private MajorMapper majorMapper;

    @Test
    void getMajorByName() {
        Major majorByName = majorMapper.getMajorByName("通信工程");
        System.out.println(majorByName);
    }
}