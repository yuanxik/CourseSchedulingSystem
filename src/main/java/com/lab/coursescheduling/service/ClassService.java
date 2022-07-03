package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Class;
import com.lab.coursescheduling.bean.vo.ClassVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;

import java.util.List;

public interface ClassService {
    //增
    ServerResponse<String> addClass(String classNum,String className,String majorName);
    //删
    ServerResponse<String> deleteClass(Integer classId);
    //改
    ServerResponse<String> updateClass(Integer classId,String classNum,String className,String majorName);
    //查
    Class getClass(String classNum);
    //
    ServerResponse<ClassVo> find(Integer classId);

    //分页
    ServerResponse<PageInfo<ClassVo>> findAll(Integer pageNum, Integer pageSize);
    ServerResponse<List<Class>> findAllByMajorNum();
}
