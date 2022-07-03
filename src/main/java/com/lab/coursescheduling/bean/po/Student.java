package com.lab.coursescheduling.bean.po;/*
    @auther
    @create ---
*/

public class Student {

    private Integer studentId;
    private String studentNum;
    private String studentName;
    private String studentPassword;
    private String role;
    private String classNum;
    private String majorNum;

    public Student() {
    }

    public Student(Integer studentId, String studentNum, String studentName, String studentPassword, String role, String classNum, String majorNum) {
        this.studentId = studentId;
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
        this.role = role;
        this.classNum = classNum;
        this.majorNum = majorNum;
    }

    public Student(String studentNum, String studentName, String studentPassword) {
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
    }

    public Student(String studentNum, String studentName, String studentPassword, String classNum, String majorNum) {
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
        this.classNum = classNum;
        this.majorNum = majorNum;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentNum='" + studentNum + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", role='" + role + '\'' +
                ", classNum='" + classNum + '\'' +
                ", majorNum='" + majorNum + '\'' +
                '}';
    }
}
