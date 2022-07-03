package com.lab.coursescheduling.web.admin;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Class;
import com.lab.coursescheduling.bean.vo.ClassVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/classInfo")
    public String toClassInfo() {
        return "administrator/class_info_admin";
    }

    @GetMapping("/find")
    @ResponseBody
    public ServerResponse<ClassVo> find(Integer classId) {
        return classService.find(classId);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public ServerResponse<PageInfo<ClassVo>> listClasses(Integer pageNum, Integer pageSize) {
        return classService.findAll(pageNum, pageSize);
    }

    @GetMapping("/addClass")
    @ResponseBody
    public ServerResponse<String> addClass(String classNum, String className, String majorName) {
        return classService.addClass(classNum, className, majorName);
    }

    @GetMapping("/updateClass")
    @ResponseBody
    public ServerResponse<String> updateClass(Integer classId, String classNum, String className, String majorName) {
        return classService.updateClass(classId, classNum, className, majorName);
    }

    @GetMapping("/deleteClass")
    @ResponseBody
    public ServerResponse<String> deleteClass(@RequestParam Integer classId) {
        return classService.deleteClass(classId);
    }

    @GetMapping("/findAllClasses")
    @ResponseBody
    public ServerResponse<List<Class>> findAllClasses() {
        return classService.findAllByMajorNum();
    }


}
