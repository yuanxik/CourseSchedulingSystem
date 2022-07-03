package com.lab.coursescheduling.mapper;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.ClassTask;
import com.lab.coursescheduling.bean.vo.ClassTaskVo;
import com.lab.coursescheduling.bean.vo.SemesterVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassTaskMapper {
    List<ClassTask> selectTaskBySemester(String semester);

    List<String> selectParamByColumn(@Param("column") String column);


    List<ClassTask> listClassTasks();

    //
    List<ClassTaskVo> findAll();
    //
    ClassTaskVo find(Integer id);
    //
    Boolean addClassTask(ClassTask classTask);
    //
    ClassTask selectRepeat(@Param("classTask") ClassTask classTask);
    //
    Boolean deleteClassTask(Integer id);
    //
    Boolean updateClassTask(Integer id,@Param("classTask") ClassTask classTask);



}
