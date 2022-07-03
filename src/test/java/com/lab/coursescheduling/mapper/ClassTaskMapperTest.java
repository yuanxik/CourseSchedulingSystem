package com.lab.coursescheduling.mapper;

import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.vo.ClassTaskVo;
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
class ClassTaskMapperTest {
    @Autowired
    private ClassTaskMapper classTaskMapper;

    @Test
    void findAll() {
        List<ClassTaskVo> all = classTaskMapper.findAll();
        for (ClassTaskVo classTaskVo:all){
            System.out.println(classTaskVo);
        }
    }

    @Test
    void find() {
        ClassTaskVo classTaskVo = classTaskMapper.find(116);
        System.out.println(classTaskVo);
    }

    @Test
    void addClassTask() {
    }

    @Test
    void deleteClassTask() {
    }

    @Test
    void updateClassTask() {
    }
}