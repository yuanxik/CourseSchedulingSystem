package com.lab.coursescheduling.service;

import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.po.ClassTask;
import com.lab.coursescheduling.bean.vo.ConstantVo;
import com.lab.coursescheduling.bean.vo.SemesterVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.ClassTaskMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---

*/

@SpringBootTest(classes = CourseSchedulingApplication.class)
class ClassTaskServiceImplTest {

    @Autowired
    private ClassTaskMapper classTaskMapper;
    @Autowired
    private ClassTaskService classTaskService;

    @Test
    void selectTaskBySemester() {

        List<ClassTask> classTasks = classTaskMapper.selectTaskBySemester("2018-2019-01");
        for (ClassTask classTask:classTasks){
            System.out.println(classTask);
        }
//        Random random = new Random();
//        for (int i=10;i<100;i++){
//            System.out.println(random.nextInt(25));
////            System.out.println( 1 + (int) (Math.random() * (25 + 1 - 1)));
//        }
    }

    @Test
    void testSelectTaskBySemester() {
        List<ClassTask> classTasks = classTaskMapper.selectTaskBySemester("2018-2019-01");
        for (ClassTask classTask:classTasks){
            System.out.println(classTask);
        }
    }

    @Test
    void selectTaskByColumn() {
        List<String> list = classTaskMapper.selectParamByColumn(ConstantVo.CLASS_NUM);
        for (String classNum:list){
            System.out.println(classNum);
        }
    }
    @Test
    void listClassTasks(){
        List<ClassTask> classTaskList = classTaskMapper.listClassTasks();
        for (ClassTask classTask:classTaskList){
            System.out.println(classTask);
        }
    }

    @Test
    void selectClassNumByColumn(){
        List<String> list = classTaskMapper.selectParamByColumn("semester");
        for (String s:list){
            System.out.println(s);
        }
    }

    @Test
    void selectSemesterByColumn(){
        ServerResponse<List<SemesterVo>> serverResponse = classTaskService.selectSemesterByColumn("semester");
        for (SemesterVo semesterVo:serverResponse.getData()){
            System.out.println(semesterVo);
        }
    }




}