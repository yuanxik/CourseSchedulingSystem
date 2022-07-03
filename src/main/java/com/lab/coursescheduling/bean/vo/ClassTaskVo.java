package com.lab.coursescheduling.bean.vo;/*
    @auther
    @create ---
*/

public class ClassTaskVo {

    private Integer id;
    private String semester;
    private String majorName;
    private String className;
    private String courseName;
    private String teacherName;
    private Integer weeksSum;
    private Integer weeksNumber;

    public ClassTaskVo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getWeeksSum() {
        return weeksSum;
    }

    public void setWeeksSum(Integer weeksSum) {
        this.weeksSum = weeksSum;
    }

    public Integer getWeeksNumber() {
        return weeksNumber;
    }

    public void setWeeksNumber(Integer weeksNumber) {
        this.weeksNumber = weeksNumber;
    }

    @Override
    public String toString() {
        return "ClassTaskVo{" +
                "id=" + id +
                ", semester='" + semester + '\'' +
                ", majorName='" + majorName + '\'' +
                ", className='" + className + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", weeksSum=" + weeksSum +
                ", weeksNumber=" + weeksNumber +
                '}';
    }
}
