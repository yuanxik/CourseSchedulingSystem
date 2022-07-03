package com.lab.coursescheduling.bean.vo;/*
    @auther
    @create ---
*/

public class PageInfoVo {
    private Integer pageNum;
    private Boolean hasNext;
    private Boolean hasPre;

    public PageInfoVo(Integer pageNum, Boolean hasNext, Boolean hasPre) {
        this.pageNum = pageNum;
        this.hasNext = hasNext;
        this.hasPre = hasPre;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Boolean getHasPre() {
        return hasPre;
    }

    public void setHasPre(Boolean hasPre) {
        this.hasPre = hasPre;
    }

    @Override
    public String toString() {
        return "PageInfoVo{" +
                "pageNum=" + pageNum +
                ", hasNext=" + hasNext +
                ", hasPre=" + hasPre +
                '}';
    }
}
