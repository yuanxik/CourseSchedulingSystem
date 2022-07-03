package com.lab.coursescheduling.service;

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.po.Student;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.bean.vo.StudentVo;
import com.lab.coursescheduling.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/
@SpringBootTest(classes = CourseSchedulingApplication.class)
class StudentServiceImplTest {

@Autowired
private StudentService studentService;

    @Test
    void checkStudentByNumAndPassword() {
        assertEquals(1,2);
    }

    @Test
    void addStuByNumAndPassword() {
    }


    @Test
    void addStusByList(){
        Student stu_1=new Student("1811530414","原希","1811530214","4","1");
        Student stu_2=new Student("1811530014","原希","1811530214","4","1");
        Student stu_3=new Student("1811530114","原希","1811530214","4","1");
        Student stu_4=new Student("1811530314","原希","1811530214","4","1");
        ArrayList<Student> list = new ArrayList<>();
        list.add(stu_1);
        list.add(stu_2);
        list.add(stu_3);
        list.add(stu_4);
    }
    @Test
    void findAll(){
        ServerResponse<PageInfo<StudentVo>> serverResponse = studentService.listStus(1, 5);
        for (StudentVo s:serverResponse.getData().getList()){
            System.out.println(s);
        }
    }
    @Test
    void addStu(){
        ServerResponse<String> serverResponse = studentService.addStu("1811530414", "原希", "1811530214", "通信工程", "通信1804");
        System.out.println(serverResponse.getMsg());
    }
}