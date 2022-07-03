package com.lab.coursescheduling.web.admin;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.ClassTask;
import com.lab.coursescheduling.bean.po.Course;
import com.lab.coursescheduling.bean.vo.CourseVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/courseInfo")
    public String courseInfo() {
        return "administrator/course_info_admin";
    }

    @GetMapping("/classScheduling")
    public String classScheduling(@RequestBody ClassTask classTask) {

        return null;
    }

    @GetMapping("/addCourse")
    @ResponseBody
    public ServerResponse<String> addCourse(String courseNum, String courseName) {
        return courseService.addCourse(courseNum, courseName);
    }

    /*
    分页
     */
    @GetMapping("/findAll")
    @ResponseBody
    public ServerResponse<PageInfo<Course>> listCourses(Integer pageNum, Integer pageSize) {
        return courseService.findAll(pageNum, pageSize);
    }
    /*
    全部查询
     */
    @GetMapping("/findAllCourses")
    @ResponseBody
    public ServerResponse<List<CourseVo>> findAllCourses(){
        return courseService.findAllCourses();
    }

    @GetMapping("/find")
    @ResponseBody
    public ServerResponse<Course> find(Integer courseId) {
        return courseService.find(courseId);
    }

    @GetMapping("/deleteCourse")
    @ResponseBody
    public ServerResponse<String> deleteCourse(@RequestParam("courseId") Integer courseId) {
        ServerResponse<String> serverResponse = courseService.deleteCourse(courseId);

        return serverResponse;
    }

    @GetMapping("/updateCourse")
    @ResponseBody
    public ServerResponse<String> updateCourse(@RequestParam("courseId") Integer courseId,
                                               @RequestParam("courseNum") String courseNum,
                                               @RequestParam("courseName") String courseName) {
        ServerResponse<String> serverResponse = courseService.updateCourse(courseId, courseNum, courseName);
        return serverResponse;
    }

}
