package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.*;
import com.lab.coursescheduling.bean.po.Class;
import com.lab.coursescheduling.bean.vo.CheckVo;
import com.lab.coursescheduling.bean.vo.ClassTaskVo;
import com.lab.coursescheduling.bean.vo.SemesterVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.*;
import com.lab.coursescheduling.util.ParamCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassTaskServiceImpl implements ClassTaskService {

    @Autowired
    private ClassTaskMapper classTaskMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Override
    public List<ClassTask> selectTaskBySemester(String semester) {
        return classTaskMapper.selectTaskBySemester(semester);
    }

    @Override
    public ServerResponse<List<SemesterVo>>  selectSemesterByColumn(String column) {
        List<String> list = classTaskMapper.selectParamByColumn(column);
        ArrayList<SemesterVo> semesterVoList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            SemesterVo semesterVo = new SemesterVo();
            semesterVo.setId(i+1);
            semesterVo.setSemesterName(list.get(i));
            semesterVoList.add(semesterVo);
        }
        return ServerResponse.createBySuccess(semesterVoList);
    }

    @Override
    public ServerResponse<PageInfo<ClassTaskVo>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ClassTaskVo> taskVoList = classTaskMapper.findAll();
        PageInfo<ClassTaskVo> pageInfo = new PageInfo<>(taskVoList);
        pageInfo.setTotal(coursePlanMapper.getTotalByTableName("class_task"));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<ClassTaskVo> find(Integer id) {
        ClassTaskVo classTaskVo = classTaskMapper.find(id);
        return ServerResponse.createBySuccess(classTaskVo);
    }

    @Override
    public ServerResponse<String> addClassTask(String courseName, String majorName, String className, String teacherName, String weeksSum, String weeksNumber, String semester) {
        CheckVo checkVo = checkClassTaskParam(weeksSum, weeksNumber);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        ClassTask classTask = new ClassTask(semester,Integer.parseInt(weeksSum),Integer.parseInt(weeksNumber));
        Course course = courseMapper.findByName(courseName);
        if (course!=null) classTask.setCourseNum(course.getCourseNum());
        Major major = majorMapper.getMajorByName(majorName);
        if (major!=null) classTask.setMajorNum(major.getMajorNum());
        Class aClass = classMapper.getClassByName(className);
        if (aClass!=null) classTask.setClassNum(aClass.getClassNum());
        Teacher teacher = teacherMapper.getTeacherByName(teacherName);
        if (teacher!=null) classTask.setTeacherNum(teacher.getTeacherNum());
        ClassTask repeat = classTaskMapper.selectRepeat(classTask);
        if (repeat!=null){
            return ServerResponse.createByErrorMessage("开课任务已存在，添加失败");
        }
        Boolean aBoolean = classTaskMapper.addClassTask(classTask);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }


    @Override
    public ServerResponse<String> deleterClassTask(Integer id) {
        ClassTaskVo classTaskVo = classTaskMapper.find(id);
        if (classTaskVo==null){
            return ServerResponse.createByErrorMessage("开课任务不存在，删除失败");
        }
        Boolean aBoolean = classTaskMapper.deleteClassTask(id);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");

    }

    @Override
    public ServerResponse<String> updateClassTask(Integer id, String courseName, String majorName, String className, String teacherName, String weeksSum, String weeksNumber, String semester) {
        CheckVo checkVo = checkClassTaskParam(weeksSum, weeksNumber);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        ClassTask classTask = new ClassTask(semester,Integer.parseInt(weeksSum),Integer.parseInt(weeksNumber));
        Course course = courseMapper.findByName(courseName);
        if (course!=null) classTask.setCourseNum(course.getCourseNum());
        Major major = majorMapper.getMajorByName(majorName);
        if (major!=null) classTask.setMajorNum(major.getMajorNum());
        Class aClass = classMapper.getClassByName(className);
        if (aClass!=null) classTask.setClassNum(aClass.getClassNum());
        Teacher teacher = teacherMapper.getTeacherByName(teacherName);
        if (teacher!=null) classTask.setTeacherNum(teacher.getTeacherNum());
        ClassTask repeat = classTaskMapper.selectRepeat(classTask);
        if (repeat!=null){
            return ServerResponse.createByErrorMessage("开课任务已存在，更新失败");
        }
        Boolean aBoolean = classTaskMapper.updateClassTask(id, classTask);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");

    }

    private CheckVo checkClassTaskParam(String weeksSum, String weeksNumber){
        if (!ParamCheckUtil.checkIntegerParamLegal(weeksSum)){
            return new CheckVo(false,"周数参数非法");
        }
        if (!ParamCheckUtil.checkIntegerParamLegal(weeksNumber)){
            return new CheckVo(false,"课时参数非法");
        }
        return new CheckVo(true);
    }

}
