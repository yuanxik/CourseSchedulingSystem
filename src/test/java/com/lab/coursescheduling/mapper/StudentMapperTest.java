package com.lab.coursescheduling.mapper;

import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.vo.StudentVo;
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
class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;
    @Test
    void selectAll() {
        List<StudentVo> studentVoList = studentMapper.selectAll();
        for (StudentVo s:studentVoList){
            System.out.println(s);
        }
    }
}