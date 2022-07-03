package com.lab.coursescheduling.mapper;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {

    Teacher checkTeacherByNameAndPassword(String teacherNum,String teacherPassword);
    //增
    Boolean addTeacher(@Param("newTeacher") Teacher newTeacher);
    //删
    Boolean deleteTeacher(Long teacherId);
    //
    Integer del(Integer teacherId);
    //
    Long getCount(@Param("tableName") String tableName);
    //改
    Boolean updateTeacher(Integer teacherId,@Param("teacher") Teacher teacher);
    //查
    Teacher getTeacher(String teacehrNum,String teacherName);
    //
    Teacher getTeacher(String teacherNum);
    //
    Teacher findTeacher(Integer id);
    //
    Teacher getTeacherByName(String teacherName);

    //分页查询
    List<Teacher> listTeachers();
    //


}
