package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Class;
import com.lab.coursescheduling.bean.po.Major;
import com.lab.coursescheduling.bean.po.Student;
import com.lab.coursescheduling.bean.po.Teacher;
import com.lab.coursescheduling.bean.vo.CheckVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.bean.vo.StudentVo;
import com.lab.coursescheduling.mapper.CoursePlanMapper;
import com.lab.coursescheduling.mapper.TeacherMapper;
import com.lab.coursescheduling.util.ParamCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;

    @Override
    public Teacher checkTeacherByNameAndPassword(String teacherNum, String teacherPassword) {
        return teacherMapper.checkTeacherByNameAndPassword(teacherNum, teacherPassword);
    }

    @Override
    public ServerResponse<String> addTeacher(String teacherName,String teacherNum,String teacherPassword,String role) {
        CheckVo checkVo = checkTeacherParam(teacherName, teacherNum, teacherPassword);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        Teacher teacher = teacherMapper.getTeacher(teacherNum);
        if (teacher!=null){
            return ServerResponse.createByErrorMessage("教师已经存在");
        }
        Boolean aBoolean = teacherMapper.addTeacher(new Teacher(teacherNum, teacherName, teacherPassword,role));
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    @Override
    public Boolean deleteTeacher(Long teacherId) {
        return teacherMapper.deleteTeacher(teacherId);
    }

    @Override
    public ServerResponse<String> del(Integer id) {
        Integer count = teacherMapper.del(id);
        if (count>0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @Override
    public ServerResponse<String> updateTeacher(Integer teacherId, Teacher teacher) {
        CheckVo checkVo = checkTeacherParam(teacher.getTeacherName(), teacher.getTeacherNum(), teacher.getTeacherPassword());
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        Teacher tcher = teacherMapper.findTeacher(teacherId);
        if (tcher==null){
            return ServerResponse.createByErrorMessage("教师不存在");
        }
        Boolean aBoolean = teacherMapper.updateTeacher(teacherId, teacher);
        if (aBoolean){
            return ServerResponse.createByErrorMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }


    @Override
    public Teacher getTeacher(String teacherNum) {
        return teacherMapper.getTeacher(teacherNum);
    }


    @Override
    public ServerResponse<PageInfo<Teacher>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Teacher> teacherList = teacherMapper.listTeachers();
        for (Teacher teacher:teacherList){
            if (teacher.getRole().equals("1")) teacher.setRole("普通用户");
            if (teacher.getRole().equals("0")) teacher.setRole("管理员");
        }
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        pageInfo.setTotal(coursePlanMapper.getTotalByTableName("teacher_info"));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<Teacher> findTeacher(Integer id) {
        return ServerResponse.createBySuccess(teacherMapper.findTeacher(id));
    }

    @Override
    public ServerResponse<List<Teacher>> findAllTeachers() {
        List<Teacher> teacherList = teacherMapper.listTeachers();
        return ServerResponse.createBySuccess(teacherList);
    }

    @Override
    public ServerResponse<Teacher> findBySession(HttpSession session) {
        Teacher teacher =(Teacher) session.getAttribute("teacher");
        return ServerResponse.createBySuccess(teacher);
    }

    private CheckVo checkTeacherParam(String teacherName,String teacherNum,String teacherPassword) {
        if (!ParamCheckUtil.checkIntegerParamLegal(CheckVo.TEACHER_NUM,teacherNum)) {
            return new CheckVo(false, "编号非法，请输入6位编号，如100001");
        }
        if (!ParamCheckUtil.checkEmpty(teacherName)) {
            return new CheckVo(false, "姓名不能为空");
        }
        if (!ParamCheckUtil.checkMixParamLegal(6, 10,teacherPassword)) {
            return new CheckVo(false, "密码非法");
        }
        return new CheckVo(true);
    }
}
