package com.lab.coursescheduling.bean.vo;/*
    @auther
    @create ---
*/

public class CoursePlanVo {
    private Integer id;

    private String majorName;

    private String className;

    private String courseName;

    private String teacherName;

    private String classTime;

    private Integer weeksSum;

    private String semester;

    private Integer weeksStart;

    private Integer weeksEnd;

    public CoursePlanVo() {
    }

    public CoursePlanVo(Integer id, String classTime, Integer weeksSum, String semester) {
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
        return "CoursePlanVo{" +
                "id=" + id +
                ", majorName='" + majorName + '\'' +
                ", className='" + className + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", classTime='" + classTime + '\'' +
                ", weeksSum=" + weeksSum +
                ", semester='" + semester + '\'' +
                ", weeksStart=" + weeksStart +
                ", weeksEnd=" + weeksEnd +
                '}';
    }
}
