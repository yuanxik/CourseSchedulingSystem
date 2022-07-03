package com.lab.coursescheduling.bean.vo;/*
    @auther
    @create ---
*/

public class CourseVo {

    private Integer id;
    private String courseNum;
    private String courseName;

    public CourseVo() {
    }

    public CourseVo(Integer id, String courseNum, String courseName) {
        this.id = id;
        this.courseNum = courseNum;
        this.courseName = courseName;
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

    @Override
    public String toString() {
        return "CourseVo{" +
                "id=" + id +
                ", courseNum='" + courseNum + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
