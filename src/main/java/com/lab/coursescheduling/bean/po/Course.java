package com.lab.coursescheduling.bean.po;/*
    @auther
    @create ---
*/

public class Course {

    private Integer id;
    private String courseNum;
    private String courseName;
    private String courseAttr;

    public Course() {
    }

    public Course(String courseNum, String courseName) {
        this.courseNum = courseNum;
        this.courseName = courseName;
    }

    public Course(Integer id, String courseNum, String courseName, String courseAttr) {
        this.id = id;
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.courseAttr = courseAttr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseAttr() {
        return courseAttr;
    }

    public void setCourseAttr(String courseAttr) {
        this.courseAttr = courseAttr;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseNum='" + courseNum + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseAttr='" + courseAttr + '\'' +
                '}';
    }
}
