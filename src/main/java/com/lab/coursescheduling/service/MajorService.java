package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Major;
import com.lab.coursescheduling.bean.vo.ServerResponse;

import java.util.List;

public interface MajorService {
    //增
    ServerResponse<String> addMajor(String majorNum,String majorName);
    //删
    ServerResponse<String> deleteMajor(Integer majorId);
    //改
    ServerResponse<String> updateMajor(Integer majorId,String majorNum,String majorName);


    //根据num查name用到
    Major getMajorByNum(String majorNum);
    ServerResponse<Major> find(Integer majorId);
    ServerResponse<PageInfo<Major>> findAll(Integer pageNum,Integer pageSize);
    ServerResponse<List<Major>> findAllMajors();
}
