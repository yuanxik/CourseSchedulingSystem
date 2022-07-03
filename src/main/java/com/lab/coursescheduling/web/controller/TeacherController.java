package com.lab.coursescheduling.web.controller;/*
    @auther
    @create ---
*/

        import com.github.pagehelper.PageInfo;
        import com.lab.coursescheduling.bean.po.Teacher;
        import com.lab.coursescheduling.bean.vo.ServerResponse;
        import com.lab.coursescheduling.bean.vo.StudentVo;
        import com.lab.coursescheduling.service.TeacherService;
        import jdk.nashorn.internal.runtime.logging.Logger;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.ModelAndView;
        import org.springframework.web.servlet.mvc.support.RedirectAttributes;

        import javax.servlet.http.HttpSession;
        import java.util.List;

        import static org.springframework.jdbc.support.JdbcUtils.isNumeric;


@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teacherInfo")
    public String toTeacherInfo() {
        return "administrator/teacher_info_admin";
    }

    @GetMapping("/toOwnInfo")
    public String toOwnInfo(){
        return "stuAndTeacher/info_teacher";
    }

    @GetMapping("/courseTable")
    public String courseTable(){
        return "stuAndTeacher/courseTable_teacher";
    }



    /*

     */
    @GetMapping("/find")
    @ResponseBody
    public ServerResponse<Teacher> findTeacher(Integer id){
        return teacherService.findTeacher(id);
    }
    /*
    分页查询
     */
    @GetMapping("/findAll")
    @ResponseBody
    public ServerResponse<PageInfo<Teacher>> findAll(Integer pageNum,Integer pageSize){
        return teacherService.findAll(pageNum,pageSize);
    }
    //增
    @GetMapping("/addTeacher")
    @ResponseBody
    public ServerResponse<String> addTeacher(@RequestParam("teacherNum") String teacherNum,
                                             @RequestParam("teacherPassword") String teacherPassword,
                                             @RequestParam("teacherName") String teacherName,
                                             @RequestParam("role") String role) {
        return teacherService.addTeacher(teacherName,teacherNum,teacherPassword,role);
    }

    @GetMapping("/del")
    @ResponseBody
    public ServerResponse<String> del(@RequestParam("id") Integer id){
        return teacherService.del(id);
    }

    //改
    @GetMapping("/updateTeacher")
    @ResponseBody
    public ServerResponse<String> updateTeacher(@RequestParam("teacherNum") String teacherNum,
                                @RequestParam("teacherId") Integer teacherId,
                                @RequestParam("teacherName") String teacherName,
                                @RequestParam("teacherPassword") String teacherPassword,
                                @RequestParam("role") String role) {
        Teacher teacher = new Teacher(teacherNum, teacherName, teacherPassword,role);
        return teacherService.updateTeacher(teacherId,teacher);
    }
    @GetMapping("/findAllTeachers")
    @ResponseBody
    public ServerResponse<List<Teacher>> findAllTeachers(){
        return teacherService.findAllTeachers();
    }

    @GetMapping("/findBySession")
    @ResponseBody
    public ServerResponse<Teacher> findBySession(HttpSession session){
        return teacherService.findBySession(session);
    }

}
