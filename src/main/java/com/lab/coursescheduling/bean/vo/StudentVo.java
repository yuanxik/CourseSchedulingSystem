package com.lab.coursescheduling.bean.vo;/*
    @auther
    @create ---
*/

public class StudentVo {
    private Integer studentId;
    private String studentNum;
    private String studentName;
    private String studentPassword;
    private String role;
    private String className;
    private String majorName;

    public StudentVo() {
    }

    public StudentVo(Integer studentId, String studentNum, String studentName, String studentPassword, String className, String majorName) {
        this.studentId = studentId;
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
        this.className = className;
        this.majorName = majorName;
    }

    public StudentVo(Integer studentId, String studentNum, String studentName, String studentPassword, String role, String className, String majorName) {
        this.studentId = studentId;
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
        this.role = role;
        this.className = className;
        this.majorName = majorName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public StudentVo(String studentNum, String studentName, String studentPassword) {
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                "studentId=" + studentId +
                ", studentNum='" + studentNum + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", role='" + role + '\'' +
                ", className='" + className + '\'' +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}
