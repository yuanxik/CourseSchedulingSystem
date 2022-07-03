package com.lab.coursescheduling.bean.po;/*
    @auther
    @create ---
*/

public class Major {

    private Long majorId;
    private String majorNum;
    private String majorName;

    public Major() {
    }

    public Major(String majorNum, String majorName) {
        this.majorNum = majorNum;
        this.majorName = majorName;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "Major{" +
                "majorId=" + majorId +
                ", majorNum='" + majorNum + '\'' +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}
