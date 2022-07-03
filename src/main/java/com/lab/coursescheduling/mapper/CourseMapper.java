package com.lab.coursescheduling.mapper;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseMapper {

    //分页
    List<Course> listCourses();

    //删除课程
    Boolean deleteCourse(Integer id);
    //更新课程
    Boolean updateCourse(Integer courseId,@Param("course") Course course);
    //
    Course findById(Integer courseId);
    //
    Course findByNum(String courseNum);
    //
    Course findByName(String courseName);
    //
    Boolean addCourse(@Param("course") Course course);
}
