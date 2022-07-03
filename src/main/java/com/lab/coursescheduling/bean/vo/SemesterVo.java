package com.lab.coursescheduling.bean.vo;/*
    @auther
    @create ---
*/

public class SemesterVo {
    private Integer id;
    private String semesterName;

    public SemesterVo() {
    }

    public SemesterVo(String semesterName) {
        this.semesterName = semesterName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    @Override
    public String toString() {
        return "SemesterVo{" +
                "id=" + id +
                ", semesterName='" + semesterName + '\'' +
                '}';
    }
}
