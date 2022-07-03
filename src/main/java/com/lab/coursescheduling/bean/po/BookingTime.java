package com.lab.coursescheduling.bean.po;/*
    @auther
    @create ---
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingTime {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String classTime;
    private Integer studentNumber;
    private Boolean isAllow;

    public BookingTime() {
    }

    public BookingTime(LocalDate date, String classTime, Integer studentNumber, Boolean isAllow) {
        this.date = date;
        this.classTime = classTime;
        this.studentNumber = studentNumber;
        this.isAllow = isAllow;
    }

    public BookingTime(Integer id, LocalDate date, String classTime, Integer studentNumber, Boolean isAllow) {
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

    public Boolean getAllow() {
        return isAllow;
    }

    public void setAllow(Boolean allow) {
        isAllow = allow;
    }

    @Override
    public String toString() {
        return "BookingTime{" +
                "id=" + id +
                ", date=" + date +
                ", classTime='" + classTime + '\'' +
                ", studentNumber=" + studentNumber +
                ", isAllow=" + isAllow +
                '}';
    }
}
