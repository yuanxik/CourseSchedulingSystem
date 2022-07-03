package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Major;
import com.lab.coursescheduling.bean.vo.CheckVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.CoursePlanMapper;
import com.lab.coursescheduling.mapper.MajorMapper;
import com.lab.coursescheduling.util.ParamCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Override
    public ServerResponse<String> addMajor(String majorNum,String majorName) {
        CheckVo checkVo = checkMajorParam(majorNum, majorName);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }

        Major major = majorMapper.getMajorByNum(majorNum);
        if (major!=null){
            return ServerResponse.createByErrorMessage("专业已存在，添加失败");
        }
        Boolean aBoolean = majorMapper.addMajor(new Major(majorNum, majorName));
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    @Override
    public ServerResponse<String> deleteMajor(Integer majorId) {
        Major major = majorMapper.getMajorById(majorId);
        if (major==null){
            return ServerResponse.createByErrorMessage("专业不存在，删除失败");
        }
        Boolean aBoolean = majorMapper.deleteMajor(majorId);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> updateMajor(Integer majorId,String majorNum,String majorName) {
        CheckVo checkVo = checkMajorParam(majorNum, majorName);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        Major major = majorMapper.getMajorById(majorId);
        if (major==null){
            return ServerResponse.createByErrorMessage("专业不存在，更新失败");
        }

        Boolean aBoolean = majorMapper.updateMajor(majorId, new Major(majorNum, majorName));
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public Major getMajorByNum(String majorNum) {
        return majorMapper.getMajorByNum(majorNum);
    }

    @Override
    public ServerResponse<Major> find(Integer majorId) {
        Major major = majorMapper.getMajorById(majorId);
        if (major!=null){
            return ServerResponse.createBySuccess(major);
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<PageInfo<Major>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Major> all = majorMapper.findAll();
        PageInfo<Major> pageInfo = new PageInfo<>(all);
        pageInfo.setTotal(coursePlanMapper.getTotalByTableName("major_info"));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<List<Major>> findAllMajors() {
        List<Major> majorList = majorMapper.findAll();
        return ServerResponse.createBySuccess(majorList);
    }

    private CheckVo checkMajorParam(String majorNum, String majorName){
        if (!ParamCheckUtil.checkIntegerParamLegal(CheckVo.MAJOR_NUM,majorNum)){
            return new CheckVo(false,"专业编号非法，请输入\"+CheckVo.MAJOR_NUM+\"位编号，如01\"");
        }
        if (!ParamCheckUtil.checkEmpty(majorName)){
            return new CheckVo(false,"专业名不能为空");
        }
        return new CheckVo(true);
    }
}
