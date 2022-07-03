package com.lab.coursescheduling.mapper;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.ClassTask;
import com.lab.coursescheduling.bean.po.CoursePlan;
import com.lab.coursescheduling.bean.vo.CoursePlanVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CoursePlanMapper {
    Boolean addCoursePlans(CoursePlan coursePlan);

    Boolean updateCoursePlan(ClassTask classTask);

    List<CoursePlan> findAll();
    //
    CoursePlan find(Integer id);
    //
    Integer getTotalByTableName(@Param("tableName") String tableName);

    List<String> selectParamByColumn(@Param("column") String column);

    List<CoursePlan> findAllCoursePlanBySemester(String semester);

    List<String> findAllClassTime();
}
