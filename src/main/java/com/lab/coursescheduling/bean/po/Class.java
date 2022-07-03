package com.lab.coursescheduling.bean.po;/*
    @auther
    @create ---
*/

public class Class {

    private Integer classId;
    private String classNum;
    private String className;
    private String majorNum;

    public Class() {
    }

    public Class(String classNum, String className) {
        this.classNum = classNum;
        this.className = className;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", classNum='" + classNum + '\'' +
                ", className='" + className + '\'' +
                ", majorNum='" + majorNum + '\'' +
                '}';
    }
}
