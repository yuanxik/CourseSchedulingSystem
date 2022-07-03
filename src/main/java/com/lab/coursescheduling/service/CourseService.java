package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Course;
import com.lab.coursescheduling.bean.vo.CourseVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;

import java.util.List;

public interface CourseService {

    //分页
    ServerResponse<PageInfo<Course>> findAll(Integer pageNum, Integer pageSize);
    //查询全部
    ServerResponse<List<CourseVo>> findAllCourses();
    //删除课程
    ServerResponse<String> deleteCourse(Integer id);
    //更新课程
    ServerResponse<String> updateCourse(Integer courseId,String courseNum,String courseName);
    //单个查询
    ServerResponse<Course> find(Integer courseId);
    //
    ServerResponse<String> addCourse(String courseNum,String courseName);
}
