package com.lab.coursescheduling.mapper;

import com.lab.coursescheduling.CourseSchedulingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/
@SpringBootTest(classes = CourseSchedulingApplication.class)
class CoursePlanMapperTest {
    @Autowired
    private CoursePlanMapper coursePlanMapper;

    @Test
    void findAll() {
    }

    @Test
    void find() {
    }

    @Test
    void getTotalByTableName() {
        Integer count = coursePlanMapper.getTotalByTableName("course_plan");
        System.out.println(count);
        Integer count_2 = coursePlanMapper.getTotalByTableName("student_info");
        System.out.println(count_2);
        Integer count_3 = coursePlanMapper.getTotalByTableName("class_task");
        System.out.println(count_3);
    }

    @Test
    void findAllClassTime(){
        List<String> allClassTime = coursePlanMapper.findAllClassTime();
        for (String s:allClassTime){
            System.out.println(s);
        }
    }
}