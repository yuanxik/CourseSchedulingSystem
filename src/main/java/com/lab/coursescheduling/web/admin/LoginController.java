package com.lab.coursescheduling.web.admin;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.Student;
import com.lab.coursescheduling.bean.po.Teacher;
import com.lab.coursescheduling.bean.po.User;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.service.StudentService;
import com.lab.coursescheduling.service.TeacherService;
import com.lab.coursescheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /*
    前往注册页面
     */
    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    /*
    登录方法
     */
    @GetMapping("/admin/login")
    @ResponseBody
    public ServerResponse login(String userNum, String password, String role
            , HttpSession session
            ) {
        if (role.equals("0")){
            User user = userService.checkUser(userNum);
            Teacher teacher = teacherService.getTeacher(userNum);
            if (user!=null){
                if (user.getUserPassword().equals(password)){
                    user.setUserPassword(null);
                    session.setAttribute("user", user);
                    return ServerResponse.createBySuccessMessage("user");
                }else {
                    return ServerResponse.createByErrorMessage("密码错误");
                }
            }else if (teacher!=null){
                if (teacher.getTeacherPassword().equals(password)){
                    teacher.setTeacherPassword(null);
                    session.setAttribute("teacher",teacher);
                    return ServerResponse.createBySuccessMessage("user");
                }else {
                    return ServerResponse.createByErrorMessage("密码错误");
                }
            } else {
                return ServerResponse.createByErrorMessage("用户不存在");
            }

        }else {
            Teacher teacher = teacherService.getTeacher(userNum);
            if (teacher!=null){
                if (teacher.getTeacherPassword().equals(password)){
                    teacher.setTeacherPassword(null);
                    session.setAttribute("teacher",teacher);
                    return ServerResponse.createBySuccessMessage("teacher");
                }else {
                    return ServerResponse.createByErrorMessage("密码错误");
                }
            }else {
                Student student = studentService.checkStudentByNum(userNum);
                if (student!=null){
                    if (student.getStudentPassword().equals(password)){
                        student.setStudentPassword(null);
                        session.setAttribute("stu",student);
                        return ServerResponse.createBySuccessMessage("stu");
                    }else {
                        return ServerResponse.createByErrorMessage("密码错误");
                    }
                }else {
                    return ServerResponse.createByErrorMessage("用户不存在");
                }
            }
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        if (session.getAttribute("user")!=null){
            session.removeAttribute("user");
        }else if (session.getAttribute("stu")!=null){
            session.removeAttribute("stu");
        }else {
            session.removeAttribute("teacher");
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    @ResponseBody
    public ServerResponse<String> register(String name,String num,String password,String role){
        if (role.equals("1")){
            return teacherService.addTeacher(name,num,password,"1");
        }
        if (role.equals("2")){
            return studentService.registerStu(num,name,password);
        }
        return ServerResponse.createByErrorMessage("注册失败");
    }
}
