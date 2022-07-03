package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.ClassTask;
import com.lab.coursescheduling.bean.po.CoursePlan;
import com.lab.coursescheduling.bean.vo.CoursePlanVo;
import com.lab.coursescheduling.bean.vo.SemesterVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;

import java.util.List;

public interface CoursePlanService {

    Boolean addCoursePlans(CoursePlan coursePlan);

    Boolean updateCoursePlans(ClassTask classTask);

//    List<CoursePlan> getCoursePlan();

//    PageInfo<CoursePlan> listPagePlans(Integer pageNum,Integer pageSize);

    ServerResponse<PageInfo<CoursePlanVo>> findAll(Integer pageNum,Integer pageSize);

    ServerResponse<List<SemesterVo>> findSemesters();

    ServerResponse<List<CoursePlanVo>> findAllCoursePlan(String semester);
}
