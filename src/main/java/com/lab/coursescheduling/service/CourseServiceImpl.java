package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Course;
import com.lab.coursescheduling.bean.vo.CheckVo;
import com.lab.coursescheduling.bean.vo.CourseVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.CourseMapper;
import com.lab.coursescheduling.mapper.CoursePlanMapper;
import com.lab.coursescheduling.util.ParamCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Override
    public ServerResponse<PageInfo<Course>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Course> courseList = courseMapper.listCourses();
        PageInfo<Course> pageInfo=new PageInfo<>(courseList);
        pageInfo.setTotal(coursePlanMapper.getTotalByTableName("course_info"));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<List<CourseVo>> findAllCourses() {
        List<Course> courseList = courseMapper.listCourses();
        ArrayList<CourseVo> voList=new ArrayList<>();
        for (Course course:courseList){
            CourseVo courseVo = new CourseVo();
            courseVo.setId(course.getId());
            courseVo.setCourseNum(course.getCourseNum());
            courseVo.setCourseName(course.getCourseName());
            voList.add(courseVo);
        }
        return ServerResponse.createBySuccess(voList);
    }

    @Override
    public ServerResponse<String> deleteCourse(Integer id) {
        Boolean aBoolean = courseMapper.deleteCourse(id);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> updateCourse(Integer courseId,String courseNum,String courseName) {
        CheckVo checkVo = checkCourseParam(courseNum, courseName);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        if (!ParamCheckUtil.checkIntegerParamLegal(CheckVo.COURSE_NUM,courseNum)){
            return ServerResponse.createByErrorMessage("课程编号非法，请输入五位编号如10001");
        }
        Course course = new Course(courseNum, courseName);
        Boolean aBoolean = courseMapper.updateCourse(courseId, course);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse<Course> find(Integer courseId) {
        Course course = courseMapper.findById(courseId);
        return ServerResponse.createBySuccess(course);
    }

    @Override
    public ServerResponse<String> addCourse(String courseNum, String courseName) {
        CheckVo checkVo = checkCourseParam(courseNum, courseName);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        Course course = courseMapper.findByNum(courseNum);
        if (course!=null){
            return ServerResponse.createByErrorMessage("课程已存在，添加失败");
        }
        Boolean aBoolean = courseMapper.addCourse(new Course(courseNum, courseName));
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");

    }

    private CheckVo checkCourseParam(String courseNum, String courseName){
        if (!ParamCheckUtil.checkIntegerParamLegal(CheckVo.COURSE_NUM,courseNum)){
            return new CheckVo(false,"课程编号非法，请输入五位编号如10001");
        }
        if (!ParamCheckUtil.checkEmpty(courseName)){
            return new CheckVo(false,"课程名不能为空");
        }
        return new CheckVo(true);
    }
}
