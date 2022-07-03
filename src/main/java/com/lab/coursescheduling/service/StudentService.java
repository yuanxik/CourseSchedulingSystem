package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Student;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.bean.vo.StudentVo;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface StudentService {

    Student checkStudentByNum(String studentNum);

    ServerResponse<String> addStu(String studentNum,String studentName,String studentPassword,
                                   String majorName,String className);

    ServerResponse<String> registerStu(String studentNum,String studentName,String studentPassword);

    Boolean addStusByList(@Param("stus") List<Student> stus);

    ServerResponse<PageInfo<StudentVo>> listStus(Integer pageNum, Integer pageSize);

    ServerResponse<String> updateStu(Integer studentId,String studentNum,String studentName,String studentPassword,
                                     String majorName,String className);

    ServerResponse<String> deleteStu(Integer studentId);

    Student find(Integer studentId);
    ServerResponse<StudentVo> findBySession(HttpSession session);

    ServerResponse<String> updatePasssword(String studentNum, String password);
}
