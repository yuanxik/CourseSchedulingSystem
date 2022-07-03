package com.lab.coursescheduling.web.admin;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Course;
import com.lab.coursescheduling.bean.po.CoursePlan;
import com.lab.coursescheduling.bean.vo.CoursePlanVo;
import com.lab.coursescheduling.bean.vo.SemesterVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.CoursePlanMapper;
import com.lab.coursescheduling.service.ClassTaskService;
import com.lab.coursescheduling.service.CoursePlanService;
import com.lab.coursescheduling.service.CourseScheduleService;
import com.lab.coursescheduling.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CoursePlanController {
    @Autowired
    private CoursePlanService coursePlanService;
    @Autowired
    private ClassTaskService classTaskService;
    @Autowired
    private CourseScheduleService courseScheduleService;
    @Autowired
    private CoursePlanMapper coursePlanMapper;

    @GetMapping("/admin/coursePlan/coursePlanInfo")
    public String coursePlanInfo(){
        return "administrator/course_schedule";
    }

    @GetMapping("/admin/coursePlan/courseTable")
    public String courseTableAdmin(){
        return "administrator/courseTable";
    }

    @GetMapping("/coursePlan/courseTable")
    public String coureseTable(){
        return "stuAndTeacher/courseTable";
    }



    /*
    排课
     */
    @GetMapping ("/admin/coursePlan/courseSchedule")
    @ResponseBody
    public ServerResponse<String> courseSchedule(String semester){
        List<String> list = coursePlanMapper.selectParamByColumn("semester");
        if (list.contains(semester)){
            return ServerResponse.createByErrorMessage("当前学期已排课");
        }
        Boolean aBoolean = courseScheduleService.classScheduling(semester);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("排课成功,请刷新页面");
        }else {
            return ServerResponse.createByErrorMessage("排课失败");
        }

    }
    /*
    分页查询
     */
    @GetMapping("/admin/coursePlan/findAll")
    @ResponseBody
    public ServerResponse<PageInfo<CoursePlanVo>> findAll(Integer pageNum,Integer pageSize){
        return coursePlanService.findAll(pageNum,pageSize);
    }

    @GetMapping("/coursePlan/findSemesters")
    @ResponseBody
    public ServerResponse<List<SemesterVo>> findSemesters(){
        return coursePlanService.findSemesters();
    }

    @GetMapping("/coursePlan/findAllCoursePlan")
    @ResponseBody
    public ServerResponse<List<CoursePlanVo>> findAllCoursePlan(String semester){
        return coursePlanService.findAllCoursePlan(semester);
    }
}
