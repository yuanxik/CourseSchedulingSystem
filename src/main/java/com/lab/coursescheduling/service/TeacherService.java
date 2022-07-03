package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Teacher;
import com.lab.coursescheduling.bean.vo.ServerResponse;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface TeacherService {
    Teacher checkTeacherByNameAndPassword(String teacherNum,String teacherPassword);
    //增
    ServerResponse<String> addTeacher(String teacherName,String teacherNum,String teacherPassword,String role);
    //删
    Boolean deleteTeacher(Long teacherId);
    //
    ServerResponse<String> del(Integer id);
    //改
    ServerResponse<String> updateTeacher(Integer teacherId,Teacher teacher);
    //查
    Teacher getTeacher(String teacherNum);

    //分页查询
    ServerResponse<PageInfo<Teacher>> findAll(Integer pageNum,Integer pageSize);
    //
    ServerResponse<Teacher> findTeacher(Integer id);
    //
    ServerResponse<List<Teacher>> findAllTeachers();

    ServerResponse<Teacher> findBySession(HttpSession session);
}
