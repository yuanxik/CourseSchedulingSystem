package com.lab.coursescheduling.bean.po;/*
    @auther
    @create ---
*/

public class Teacher {

    private Integer teacherId;
    private String teacherNum;
    private String teacherName;
    private String teacherPassword;
    private String role;

    public Teacher() {
    }

    public Teacher(String teacherNum, String teacherName, String teacherPassword, String role) {
        this.teacherNum = teacherNum;
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
        this.role = role;
    }

    public Teacher(String teacherNum, String teacherName, String teacherPassword) {
        this.teacherNum = teacherNum;
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherNum='" + teacherNum + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherPassword='" + teacherPassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
