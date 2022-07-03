package com.lab.coursescheduling.bean.vo;/*
    @auther
    @create ---
*/

public class ClassVo {
    private Integer classId;
    private String classNum;
    private String className;
    private String majorName;

    public ClassVo() {
    }

    public ClassVo(String classNum, String className, String majorName) {
        this.classNum = classNum;
        this.className = className;
        this.majorName = majorName;
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

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "ClassVo{" +
                "classId=" + classId +
                ", classNum='" + classNum + '\'' +
                ", className='" + className + '\'' +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}
