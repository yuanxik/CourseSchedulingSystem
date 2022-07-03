package com.lab.coursescheduling.bean.po;/*
    @auther
    @create ---
*/

public class CoursePlan {
    private Integer id;

    private String majorNum;

    private String classNum;

    private String courseNum;

    private String teacherNum;

    private String classTime;

    private Integer weeksSum;

    private String semester;

    private Integer weeksStart;

    private Integer weeksEnd;

    public CoursePlan() {
    }

    public CoursePlan(Integer id, String classTime, Integer weeksSum, String semester) {
        this.id = id;
        this.classTime = classTime;
        this.weeksSum = weeksSum;
        this.semester = semester;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public Integer getWeeksSum() {
        return weeksSum;
    }

    public void setWeeksSum(Integer weeksSum) {
        this.weeksSum = weeksSum;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getWeeksStart() {
        return weeksStart;
    }

    public void setWeeksStart(Integer weeksStart) {
        this.weeksStart = weeksStart;
    }

    public Integer getWeeksEnd() {
        return weeksEnd;
    }

    public void setWeeksEnd(Integer weeksEnd) {
        this.weeksEnd = weeksEnd;
    }

    @Override
    public String toString() {
        return "CoursePlan{" +
                "id=" + id +
                ", majorNum='" + majorNum + '\'' +
                ", classNum='" + classNum + '\'' +
                ", courseNum='" + courseNum + '\'' +
                ", teacherNum='" + teacherNum + '\'' +
                ", classTime='" + classTime + '\'' +
                ", weeksSum=" + weeksSum +
                ", semester='" + semester + '\'' +
                ", weeksStart=" + weeksStart +
                ", weeksEnd=" + weeksEnd +
                '}';
    }
}
