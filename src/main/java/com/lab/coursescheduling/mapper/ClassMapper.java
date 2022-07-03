package com.lab.coursescheduling.mapper;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.Class;
import com.lab.coursescheduling.bean.vo.ClassVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassMapper {
    //增
     Boolean addClass(@Param("newClass") Class newClass);
     //删
    Boolean deleteClass(Integer classId);
    //改
    Boolean updateClass(Integer classId,@Param("newClass") Class newClass);
    //查
    Class getClass(String classNum);
    Class getClassByName(String className);
    ClassVo getClassById(Integer classId);
    List<Class> findAllClassByMajorNum();

    //分页
    List<ClassVo> listClasses();


}
