package com.lab.coursescheduling.bean.vo;/*
    @auther
    @create ---
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class BookingTimeVo {
    public static final String Yes="可预约";
    public static final String No="不可预约";
    public static final Integer STUDENT_MAX=30;
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String classTime;
    private Integer studentNumber;
    private String isAllow;

    public BookingTimeVo(Integer id, LocalDate date, String classTime, Integer studentNumber, String isAllow) {
        this.id = id;
        this.date = date;
        this.classTime = classTime;
        this.studentNumber = studentNumber;
        this.isAllow = isAllow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getIsAllow() {
        return isAllow;
    }

    public void setIsAllow(String isAllow) {
        this.isAllow = isAllow;
    }

    @Override
    public String toString() {
        return "BookingTimeVo{" +
                "id=" + id +
                ", date=" + date +
                ", classTime=" + classTime +
                ", studentNumber=" + studentNumber +
                ", isAllow='" + isAllow + '\'' +
                '}';
    }
}
