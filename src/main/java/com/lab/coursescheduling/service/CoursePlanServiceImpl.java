package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.*;
import com.lab.coursescheduling.bean.po.Class;
import com.lab.coursescheduling.bean.vo.CoursePlanVo;
import com.lab.coursescheduling.bean.vo.SemesterVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoursePlanServiceImpl implements CoursePlanService {
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Boolean addCoursePlans(CoursePlan coursePlan) {
        return null;
    }

    @Override
    public Boolean updateCoursePlans(ClassTask classTask) {
        return null;
    }

//    @Override
//    public List<CoursePlan> getCoursePlan() {
//        return coursePlanMapper.getCoursePlan();
//    }

//    @Override
//    public PageInfo<CoursePlan> listPagePlans(Integer pageNum, Integer pageSize) {
//        PageHelper.startPage(pageNum,pageSize);
//        List<CoursePlan> coursePlanList = coursePlanMapper.getCoursePlan();
//        return new PageInfo<>(coursePlanList);
//    }

    @Override
    public ServerResponse<PageInfo<CoursePlanVo>> findAll(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CoursePlan> coursePlanList = coursePlanMapper.findAll();
        ArrayList<CoursePlanVo> coursePlanVos=new ArrayList<>();
        for (CoursePlan coursePlan:coursePlanList){
            CoursePlanVo coursePlanVo = new CoursePlanVo(coursePlan.getId(),coursePlan.getClassTime(),coursePlan.getWeeksSum(),coursePlan.getSemester());
            Course course = courseMapper.findByNum(coursePlan.getCourseNum());
            if (course!=null) coursePlanVo.setCourseName(course.getCourseName());
            Major major = majorMapper.getMajorByNum(coursePlan.getMajorNum());
            if (major!=null) coursePlanVo.setMajorName(major.getMajorName());
            Class aClass = classMapper.getClass(coursePlan.getClassNum());
            if (aClass!=null) coursePlanVo.setClassName(aClass.getClassName());
            Teacher teacher = teacherMapper.getTeacher(coursePlan.getTeacherNum());
            if (teacher!=null) coursePlanVo.setTeacherName(teacher.getTeacherName());
            coursePlanVos.add(coursePlanVo);
        }
        PageInfo<CoursePlanVo> pageInfo=new PageInfo<>(coursePlanVos);
        pageInfo.setTotal(coursePlanMapper.getTotalByTableName("course_plan"));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<List<SemesterVo>> findSemesters() {
        List<String> semesterList = coursePlanMapper.selectParamByColumn("semester");
        ArrayList<SemesterVo> semesterVos = new ArrayList<>();
        for (String s:semesterList){
            SemesterVo semesterVo = new SemesterVo(s);
            semesterVos.add(semesterVo);
        }
        return ServerResponse.createBySuccess(semesterVos);
    }

    @Override
    public ServerResponse<List<CoursePlanVo>> findAllCoursePlan(String semester) {
        List<CoursePlan> coursePlanList=coursePlanMapper.findAllCoursePlanBySemester(semester);
        ArrayList<CoursePlanVo> coursePlanVos=new ArrayList<>();
        for (CoursePlan coursePlan:coursePlanList){
            CoursePlanVo coursePlanVo = new CoursePlanVo(coursePlan.getId(),coursePlan.getClassTime(),coursePlan.getWeeksSum(),coursePlan.getSemester());
            Course course = courseMapper.findByNum(coursePlan.getCourseNum());
            if (course!=null) coursePlanVo.setCourseName(course.getCourseName());
            Major major = majorMapper.getMajorByNum(coursePlan.getMajorNum());
            if (major!=null) coursePlanVo.setMajorName(major.getMajorName());
            Class aClass = classMapper.getClass(coursePlan.getClassNum());
            if (aClass!=null) coursePlanVo.setClassName(aClass.getClassName());
            Teacher teacher = teacherMapper.getTeacher(coursePlan.getTeacherNum());
            if (teacher!=null) coursePlanVo.setTeacherName(teacher.getTeacherName());
            coursePlanVos.add(coursePlanVo);
        }
        return ServerResponse.createBySuccess(coursePlanVos);
    }
}
