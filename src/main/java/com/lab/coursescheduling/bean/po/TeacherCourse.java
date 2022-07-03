package com.lab.coursescheduling.bean.po;/*
    @auther
    @create ---
*/

public class TeacherCourse {
    private Long id;

    private String teacherNum;

    private String courseNum;

    private String classNum;

    private String classTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    @Override
    public String toString() {
        return "TeacherCourse{" +
                "id=" + id +
                ", teacherNum='" + teacherNum + '\'' +
                ", courseNum='" + courseNum + '\'' +
                ", classNum='" + classNum + '\'' +
                '}';
    }
}
