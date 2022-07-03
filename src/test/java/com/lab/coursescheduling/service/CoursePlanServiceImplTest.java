package com.lab.coursescheduling.service;

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.po.CoursePlan;
import com.lab.coursescheduling.bean.vo.CoursePlanVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.CoursePlanMapper;
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
class CoursePlanServiceImplTest {
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Autowired
    private CoursePlanService coursePlanService;

    @Test
    void addCoursePlans() {
    }

    @Test
    void updateCoursePlans() {
    }

    @Test
    void getCoursePlan() {
//        List<CoursePlan> coursePlanList = coursePlanMapper.getCoursePlan();
//        ArrayList list=new ArrayList();
//        for (CoursePlan coursePlan:coursePlanList){
//            String classTime=coursePlan.getClassTime();
//            if (!list.contains(classTime)){
//                list.add(classTime);
//            }
//        }
//        System.out.println(list.size());
    }

    @Test
    void findAll() {
        ServerResponse<PageInfo<CoursePlanVo>> serverResponse = coursePlanService.findAll(1, 10);
        for (CoursePlanVo coursePlanVo:serverResponse.getData().getList()){
            System.out.println(coursePlanVo);
        }
        System.out.println(serverResponse.getData().getTotal());
    }
}