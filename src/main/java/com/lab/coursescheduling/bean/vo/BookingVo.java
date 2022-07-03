package com.lab.coursescheduling.bean.vo;/*
    @auther
    @create ---
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class BookingVo {

    private Integer id;
    private String studentNum;
    private String studentName;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String classTime;

    public BookingVo() {
    }

    public BookingVo(Integer id, String studentNum, String studentName, LocalDate date, String classTime) {
        this.id = id;
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.date = date;
        this.classTime = classTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    @Override
    public String toString() {
        return "BookingVo{" +
                "id=" + id +
                ", studentNum='" + studentNum + '\'' +
                ", studentName='" + studentName + '\'' +
                ", date=" + date +
                ", classTime='" + classTime + '\'' +
                '}';
    }
}
