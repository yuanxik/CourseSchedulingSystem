package com.lab.coursescheduling.bean.po;/*
    @auther
    @create ---
*/

public class ClassTask {
    private Integer id;
    private String semester;
    private String majorNum;
    private String classNum;
    private String courseNum;
    private String teacherNum;
    private String courseAttr;
    private Integer studentNumber;
    private Integer weeksSum;
    private Integer weeksNumber;
    private String classTime;
    private String isFix;

    public ClassTask() {
    }

    public ClassTask(String semester, Integer weeksSum, Integer weeksNumber) {
        this.semester = semester;
        this.weeksSum = weeksSum;
        this.weeksNumber = weeksNumber;
    }

    public ClassTask(String semester, String majorNum, String classNum, String courseNum, String teacherNum, String courseAttr, Integer studentNumber, Integer weeksSum, Integer weeksNumber, String classTime, String isFix) {
        this.semester = semester;
        this.majorNum = majorNum;
        this.classNum = classNum;
        this.courseNum = courseNum;
        this.teacherNum = teacherNum;
        this.courseAttr = courseAttr;
        this.studentNumber = studentNumber;
        this.weeksSum = weeksSum;
        this.weeksNumber = weeksNumber;
        this.classTime = classTime;
        this.isFix = isFix;
    }

    public ClassTask(Integer id, String semester, String majorNum, String classNum, String courseNum, String teacherNum, String courseAttr, Integer studentNumber, Integer weeksSum, Integer weeksNumber, String classTime, String isFix) {
        this.id = id;
        this.semester = semester;
        this.majorNum = majorNum;
        this.classNum = classNum;
        this.courseNum = courseNum;
        this.teacherNum = teacherNum;
        this.courseAttr = courseAttr;
        this.studentNumber = studentNumber;
        this.weeksSum = weeksSum;
        this.weeksNumber = weeksNumber;
        this.classTime = classTime;
        this.isFix = isFix;
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

    public String getCourseAttr() {
        return courseAttr;
    }

    public void setCourseAttr(String courseAttr) {
        this.courseAttr = courseAttr;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
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

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getIsFix() {
        return isFix;
    }

    public void setIsFix(String isFix) {
        this.isFix = isFix;
    }

    @Override
    public String toString() {
        return "ClassTask{" +
                "id=" + id +
                ", semester='" + semester + '\'' +
                ", majorNum='" + majorNum + '\'' +
                ", classNum='" + classNum + '\'' +
                ", courseNum='" + courseNum + '\'' +
                ", teacherNum='" + teacherNum + '\'' +
                ", courseAttr='" + courseAttr + '\'' +
                ", studentNumber=" + studentNumber +
                ", weeksSum=" + weeksSum +
                ", weeksNumber=" + weeksNumber +
                ", classTime='" + classTime + '\'' +
                ", isFix='" + isFix + '\'' +
                '}';
    }
}
