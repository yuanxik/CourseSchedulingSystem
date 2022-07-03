package com.lab.coursescheduling.bean.po;/*
    @auther
    @create ---
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Booking {

    private Integer id;
    private String studentNum;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String classTime;

    public Booking() {
    }

    public Booking(Integer id, String studentNum, LocalDate date, String classTime) {
        this.id = id;
        this.studentNum = studentNum;
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
        return "Booking{" +
                "id=" + id +
                ", studentNum='" + studentNum + '\'' +
                ", date=" + date +
                ", classTime='" + classTime + '\'' +
                '}';
    }
}
