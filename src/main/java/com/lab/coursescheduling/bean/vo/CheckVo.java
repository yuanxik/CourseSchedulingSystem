package com.lab.coursescheduling.bean.vo;
/*
    @auther
    @create ---
    封装校验参数
*/

public class CheckVo {

    private Boolean flag;
    private String msg;

    public static final Integer STUDENT_NUM=10;

    public static final Integer COURSE_NUM=5;

    public static final Integer MAJOR_NUM=2;

    public static final Integer CLASS_NUM=8;

    public static final Integer TEACHER_NUM=6;

    public CheckVo() {
    }

    public CheckVo(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public CheckVo(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CheckVo{" +
                "flag=" + flag +
                ", msg='" + msg + '\'' +
                '}';
    }
}
