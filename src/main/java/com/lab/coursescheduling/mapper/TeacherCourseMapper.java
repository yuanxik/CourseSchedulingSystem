package com.lab.coursescheduling.mapper;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.CoursePlan;
import com.lab.coursescheduling.bean.po.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCourseMapper {

    Boolean addTeacherCourse(CoursePlan coursePlan);


}
