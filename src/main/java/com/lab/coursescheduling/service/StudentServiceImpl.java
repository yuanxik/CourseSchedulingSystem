package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/




import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Class;
import com.lab.coursescheduling.bean.po.Major;
import com.lab.coursescheduling.bean.po.Student;
import com.lab.coursescheduling.bean.vo.CheckVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.bean.vo.StudentVo;
import com.lab.coursescheduling.mapper.ClassMapper;
import com.lab.coursescheduling.mapper.CoursePlanMapper;
import com.lab.coursescheduling.mapper.MajorMapper;
import com.lab.coursescheduling.mapper.StudentMapper;
import com.lab.coursescheduling.util.PageUtil;
import com.lab.coursescheduling.util.ParamCheckUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Override
    public Student checkStudentByNum(String studentNum) {
        return studentMapper.getStudentByNum(studentNum);
    }

    @Override
    public ServerResponse<String> addStu(String studentNum,String studentName,String studentPassword,
                                   String majorName,String className) {
        CheckVo checkVo = checkStuParam(studentNum, studentName, studentPassword);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        Student stu= studentMapper.findByNum(studentNum);
        if (stu!=null){
            return ServerResponse.createByErrorMessage("学生已存在，添加失败");
        }
        Major major = majorMapper.getMajorByName(majorName);
        Class aClass = classMapper.getClassByName(className);
        if (major==null||aClass==null){
            return ServerResponse.createByErrorMessage("添加失败");
        }
        Student student = new Student(studentNum, studentName, studentPassword,aClass.getClassNum(),major.getMajorNum());
        Boolean aBoolean = studentMapper.addStu(student);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    @Override
    public ServerResponse<String> registerStu(String studentNum, String studentName, String studentPassword) {
        CheckVo checkVo = checkStuParam(studentNum, studentName, studentPassword);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        Student stu= studentMapper.findByNum(studentNum);
        if (stu!=null){
            return ServerResponse.createByErrorMessage("学生已存在，注册失败");
        }
        Student student = new Student(studentNum,studentName,studentPassword);
        Boolean aBoolean = studentMapper.addStu(student);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("注册成功");
        }
        return ServerResponse.createByErrorMessage("注册失败");
    }


    @Override
    public Boolean addStusByList(List<Student> stus) {
        return studentMapper.addStuByList(stus);
    }

    /*
    分页查询
     */
    @Override
    public ServerResponse<PageInfo<StudentVo>> listStus(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        List<StudentVo> studentVoList = studentMapper.selectAll();
        PageInfo<StudentVo> pageInfo = new PageInfo<>(studentVoList);
        pageInfo.setTotal(coursePlanMapper.getTotalByTableName("student_info"));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<String> updateStu(Integer studentId,String studentNum,String studentName,String studentPassword,
                                            String majorName,String className) {
        CheckVo checkVo = checkStuParam(studentNum, studentName, studentPassword);
        if (!checkVo.getFlag()){
            return ServerResponse.createByErrorMessage(checkVo.getMsg());
        }
        Major major = majorMapper.getMajorByName(majorName);
        Class aClass = classMapper.getClassByName(className);
        Student student = new Student(studentNum, studentName, studentPassword,aClass.getClassNum(),major.getMajorNum());
        Boolean aBoolean = studentMapper.updateStuInfo(studentId, student);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse<String> deleteStu(Integer studentId) {
        Student student = studentMapper.find(studentId);
        if (student==null){
            return ServerResponse.createByErrorMessage("学生不存在，删除失败");
        }
        Boolean aBoolean = studentMapper.deleteStu(studentId);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");

    }

    @Override
    public Student find(Integer studentId) {
        return studentMapper.find(studentId);
    }

    @Override
    public ServerResponse<StudentVo> findBySession(HttpSession session) {
        Student stu =(Student) session.getAttribute("stu");
        Major major = majorMapper.getMajorByNum(stu.getMajorNum());
        Class aClass = classMapper.getClass(stu.getClassNum());
        StudentVo studentVo = new StudentVo(stu.getStudentId(), stu.getStudentNum(), stu.getStudentName(), stu.getStudentPassword(), aClass.getClassName(), major.getMajorName());
        return ServerResponse.createBySuccess(studentVo);
    }

    @Override
    public ServerResponse<String> updatePasssword(String studentNum, String password) {
        if (!ParamCheckUtil.checkMixParamLegal(6,10,password)){
            return ServerResponse.createByErrorMessage("密码非法");
        }
        Boolean aBoolean=studentMapper.updatePassword(studentNum,password);
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    private CheckVo checkStuParam(String studentNum,String studentName,String studentPassword){
        if (!ParamCheckUtil.checkIntegerParamLegal(CheckVo.STUDENT_NUM,studentNum)){
            return new CheckVo(false,"学号非法，请输入10位编号如181530214");
        }
        if (!ParamCheckUtil.checkEmpty(studentName)){
            return new CheckVo(false,"姓名不能为空");
        }
        if (!ParamCheckUtil.checkMixParamLegal(6,10,studentPassword)){
            return new CheckVo(false,"密码非法");
        }
        return new CheckVo(true);
    }

}
