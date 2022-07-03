package com.lab.coursescheduling.mapper;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.Major;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorMapper {
    //增
    Boolean addMajor(@Param("newMajor") Major newMajor);
    //删
    Boolean deleteMajor(Integer majorId);
    //改
    Boolean updateMajor(Integer majorId,@Param("major") Major major);
    //查
    Major getMajorById(Integer majorId);
    Major getMajorByNum(String majorNum);
    Major getMajorByName(String majorName);
    List<Major> findAll();
}
