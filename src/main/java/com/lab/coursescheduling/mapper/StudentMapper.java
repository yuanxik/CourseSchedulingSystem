package com.lab.coursescheduling.mapper;/*
    @auther
    @create ---
*/


import com.lab.coursescheduling.bean.po.Student;

import com.lab.coursescheduling.bean.vo.StudentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface StudentMapper {


    Student getStudentByNum(String studentNum);

    Boolean addStu(@Param("student") Student student);

    Boolean addStuByList(@Param("stus") List<Student> stus);

    List<StudentVo> selectAll();

    Boolean updateStuInfo(Integer studentId,@Param("newStu") Student newStu);

    Boolean deleteStu(Integer studentId);

    Student find(Integer studentId);

    Student findByNum(String studentNum);

    Boolean updatePassword(String studentNum, String password);
}
