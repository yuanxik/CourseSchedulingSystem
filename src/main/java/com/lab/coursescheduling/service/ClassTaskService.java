package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.ClassTask;
import com.lab.coursescheduling.bean.vo.ClassTaskVo;
import com.lab.coursescheduling.bean.vo.SemesterVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;

import java.util.List;

public interface ClassTaskService {

    List<ClassTask> selectTaskBySemester(String semester);

    ServerResponse<List<SemesterVo>> selectSemesterByColumn(String column);
    //分页
    ServerResponse<PageInfo<ClassTaskVo>> findAll(Integer pageNum, Integer pageSize);
    //
    ServerResponse<ClassTaskVo> find(Integer id);
    //
    ServerResponse<String> addClassTask(String courseName,String majorName,String className,String teacherName,
                                        String weeksSum,String weeksNumber,String semester);
    //
    ServerResponse<String> deleterClassTask(Integer id);
    //
    ServerResponse<String> updateClassTask(Integer id,String courseName,String majorName,String className,String teacherName,
                                           String weeksSum,String weeksNumber,String semester);




}
