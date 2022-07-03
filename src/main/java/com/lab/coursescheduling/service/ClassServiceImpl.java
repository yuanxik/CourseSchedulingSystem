package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Class;
import com.lab.coursescheduling.bean.po.Major;
import com.lab.coursescheduling.bean.vo.CheckVo;
import com.lab.coursescheduling.bean.vo.ClassVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.ClassMapper;
import com.lab.coursescheduling.mapper.CoursePlanMapper;
import com.lab.coursescheduling.mapper.MajorMapper;
import com.lab.coursescheduling.util.ParamCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Override
    public ServerResponse<String> addClass(String classNum,String className,String majorName) {
        CheckVo checkVo = checkClassParam(classNum, className);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        Class aClass = classMapper.getClass(classNum);
        if (aClass!=null){
            return ServerResponse.createByErrorMessage("班级已存在，添加失败");
        }
        Major major = majorMapper.getMajorByName(majorName);
        Class aCla = new Class(classNum, className);
        if (major==null){
            aCla.setMajorNum(null);
        }else {
            aCla.setMajorNum(major.getMajorNum());
        }
        Boolean aBoolean = classMapper.addClass(aCla);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    @Override
    public  ServerResponse<String> deleteClass(Integer classId) {
        Boolean aBoolean = classMapper.deleteClass(classId);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> updateClass(Integer classId,String classNum,String className,String majorName) {
        ClassVo classById = classMapper.getClassById(classId);
        if (classById==null){
            return ServerResponse.createByErrorMessage("班级不存在,更新失败");
        }
        CheckVo checkVo = checkClassParam(classNum, className);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        Major major = majorMapper.getMajorByName(majorName);
        Class aClass = new Class(classNum, className);
        if (major==null){
            aClass.setMajorNum(null);
        }else {
            aClass.setMajorNum(major.getMajorNum());
        }
        Boolean aBoolean = classMapper.updateClass(classId, aClass);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public Class getClass(String classNum) {
        return classMapper.getClass(classNum);
    }

    @Override
    public ServerResponse<ClassVo> find(Integer classId) {
        ClassVo classById = classMapper.getClassById(classId);
        return ServerResponse.createBySuccess(classById);
    }

    @Override
    public ServerResponse<PageInfo<ClassVo>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ClassVo> classList = classMapper.listClasses();
        PageInfo<ClassVo> pageInfo = new PageInfo<>(classList);
        pageInfo.setTotal(coursePlanMapper.getTotalByTableName("class_info"));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<List<Class>> findAllByMajorNum() {
        List<Class> classList = classMapper.findAllClassByMajorNum();
        return ServerResponse.createBySuccess(classList);
    }

    private CheckVo checkClassParam(String classNum,String className){
        if (!ParamCheckUtil.checkIntegerParamLegal(CheckVo.CLASS_NUM,classNum)){

            return new CheckVo(false,"班级编号非法,请输入八位编号如20180101");
        }
        if (!ParamCheckUtil.checkEmpty(className)){
            return new CheckVo(false,"班级名不能为空");
        }
        return new CheckVo(true);
    }
}
