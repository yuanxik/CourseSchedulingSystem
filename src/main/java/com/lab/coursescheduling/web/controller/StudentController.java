package com.lab.coursescheduling.web.controller;/*
    @auther
    @create ---
*/


import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.BookingTime;
import com.lab.coursescheduling.bean.po.Class;
import com.lab.coursescheduling.bean.po.Major;
import com.lab.coursescheduling.bean.po.Student;
import com.lab.coursescheduling.bean.vo.BookingTimeVo;
import com.lab.coursescheduling.bean.vo.BookingVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.bean.vo.StudentVo;
import com.lab.coursescheduling.mapper.ClassMapper;
import com.lab.coursescheduling.mapper.MajorMapper;
import com.lab.coursescheduling.service.BookingService;
import com.lab.coursescheduling.service.ClassService;
import com.lab.coursescheduling.service.MajorService;
import com.lab.coursescheduling.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ClassService classService;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/stuInfo")
    public String toStuInfo() {
        return "administrator/stu_info_admin";
    }

    @GetMapping("/toAddStus")
    public String toAddStus(){
        return "administrator/stu_listadd";
    }
    @GetMapping("/ownBooking")
    public String ownBooking(){
        return "stuAndTeacher/booking_stu";
    }

    @GetMapping("/ownInfo")
    public String ownInfo(){
        return "stuAndTeacher/info";
    }
    /*
    前往预约页面
     */
    @GetMapping("/booking/bookingInfo")
    public String bookingInfo(){
        return "stuAndTeacher/bookingTime_stu";
    }


    /*
    增
     */
    @GetMapping("/addStu")
    @ResponseBody
    public ServerResponse<String> addStu(@RequestParam("studentNum") String studentNum,
                         @RequestParam("studentName") String studentName,
                         @RequestParam("studentPassword") String studentPassword,
                         @RequestParam("className") String className,
                         @RequestParam("majorName") String majorName) {
        return  studentService.addStu(studentNum,studentName,studentPassword,majorName,className);
    }

    /*
    分页查询
     */
    @GetMapping("/findAll")
    @ResponseBody
    public ServerResponse<PageInfo<StudentVo>> findAll(Integer pageNum, Integer pageSize) {
        ServerResponse<PageInfo<StudentVo>> serverResponse = studentService.listStus(pageNum, pageSize);
        return serverResponse;
    }

    /*
    编辑学生信息
     */
    @GetMapping("/updateStu")
    @ResponseBody
    public ServerResponse<String> updateStuInfo(@RequestParam Integer studentId,@RequestParam("studentNum") String studentNum,
                              @RequestParam("studentName") String studentName, @RequestParam("studentPassword") String studentPassword,
                              @RequestParam("className") String className, @RequestParam("majorName") String majorName) {
        ServerResponse<String> serverResponse = studentService.updateStu(studentId, studentNum, studentName, studentPassword,majorName,className);
        return serverResponse;
    }
    /*
    删除学生信息
     */
    @GetMapping("/deleteStu")
    @ResponseBody
    public ServerResponse<String> deleteStu(@RequestParam(value = "studentId") Integer studentId) {
        ServerResponse<String> serverResponse = studentService.deleteStu(studentId);
        return serverResponse;
    }

    @GetMapping("/find")
    @ResponseBody
    public ServerResponse<StudentVo> find(@RequestParam("studentId")Integer studentId){
        Student student = studentService.find(studentId);
        Major major = majorMapper.getMajorByNum(student.getMajorNum());
        Class aClass = classMapper.getClass(student.getClassNum());
        StudentVo studentVo = new StudentVo(student.getStudentNum(), student.getStudentName(), student.getStudentPassword());
        if (major==null) {
            studentVo.setMajorName(null);
        }else {
            studentVo.setMajorName(major.getMajorName());
        }
        if (aClass==null){
            studentVo.setClassName(null);
        }else {
            studentVo.setClassName(aClass.getClassName());
        }
        return ServerResponse.createBySuccess(studentVo);
    }

    @GetMapping("/findBySession")
    @ResponseBody
    public ServerResponse<StudentVo> findBySession(HttpSession session){
        return studentService.findBySession(session);
    }

    @GetMapping("/findAllMajors")
    @ResponseBody
    public ServerResponse<List<Major>> findAllMajors(){
        return majorService.findAllMajors();
    }

    @GetMapping("/findAllClasses")
    @ResponseBody
    public ServerResponse<List<Class>> findAllClasses() {
        return classService.findAllByMajorNum();
    }

    @GetMapping("/findAllOwnBooking")
    @ResponseBody
    public ServerResponse<PageInfo<BookingVo>> findAllOwnBooking(HttpSession session,Integer pageNum,Integer pageSize){
        return bookingService.findAllOwnBooking(session,pageNum,pageSize);
        }

    @GetMapping("/findAllAllowTime")
    @ResponseBody
    public ServerResponse<PageInfo<BookingTimeVo>> toBooking(){
        return bookingService.findAllowBookingTime();
    }

    @GetMapping("/booking")
    @ResponseBody
    public ServerResponse<String> booking(String date,String classTime,HttpSession session){
        return bookingService.booking(date,classTime,session);
    }
    @GetMapping("/cancelBooking")
    @ResponseBody
    public ServerResponse<String> cannelBooking(String date,String classTime,String studentNum){
        return bookingService.cancelBooking(date,classTime,studentNum);
    }

    @GetMapping("/updatePassword")
    @ResponseBody
    public ServerResponse<String> updatePasssword(String studentNum,String password){
        return studentService.updatePasssword(studentNum,password);
    }
}
