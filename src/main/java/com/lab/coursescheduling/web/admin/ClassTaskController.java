package com.lab.coursescheduling.web.admin;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.ClassTask;
import com.lab.coursescheduling.bean.vo.ClassTaskVo;
import com.lab.coursescheduling.bean.vo.SemesterVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.service.ClassTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/classTask")
public class ClassTaskController {
    @Autowired
    private ClassTaskService classTaskService;

    @GetMapping("/classTaskInfo")
    public String toSchedule(){
        return "administrator/class_task_info";
    }

    /*
    分页查询
     */
    @GetMapping("/findAll")
    @ResponseBody
    public ServerResponse<PageInfo<ClassTaskVo>> findAll(Integer pageNum, Integer pageSize){
        return classTaskService.findAll(pageNum,pageSize);
    }

    @GetMapping("/find")
    @ResponseBody
    public ServerResponse<ClassTaskVo> find(Integer id){
        return classTaskService.find(id);

    }

    @GetMapping("/addClassTask")
    @ResponseBody
    public ServerResponse<String> addClassTask(String courseName, String majorName,
                                               String className, String teacherName,
                                               String weeksSum, String weeksNumber,
                                               String semester){
        return classTaskService.addClassTask(courseName,majorName,className,teacherName,weeksSum,weeksNumber,semester);
    }

    @GetMapping("/updateClassTask")
    @ResponseBody
    public ServerResponse<String> updateClassTask(
                                                  Integer id,String courseName,
                                                  String majorName, String className,
                                                  String teacherName, String weeksSum,
                                                  String weeksNumber, String semester){
        return classTaskService.updateClassTask(id,courseName,majorName,className,teacherName,weeksSum,weeksNumber,semester);
    }

    @GetMapping("/deleteClassTask")
    @ResponseBody
    public ServerResponse<String> deleteClassTask( Integer id){
        return classTaskService.deleterClassTask(id);
    }

    @GetMapping("/findSemesters")
    @ResponseBody
    public ServerResponse<List<SemesterVo>> findSemesters(){
        return classTaskService.selectSemesterByColumn("semester");
    }


}
