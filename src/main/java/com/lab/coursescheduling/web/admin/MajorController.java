package com.lab.coursescheduling.web.admin;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Major;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/major")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @GetMapping("/majorInfo")
    public String toStuInfo() {
        return "administrator/major_info_admin";
    }

    @GetMapping("/addMajor")
    @ResponseBody
    public ServerResponse<String> addMajor(String majorNum,String majorName){
        return majorService.addMajor(majorNum,majorName);
    }

    @GetMapping("/find")
    @ResponseBody
    public ServerResponse<Major> find(Integer majorId){
        return majorService.find(majorId);
    }
    @GetMapping("/findAll")
    @ResponseBody
    public ServerResponse<PageInfo<Major>> findAll(Integer pageNum,Integer pageSize){
        return majorService.findAll(pageNum,pageSize);
    }
    @GetMapping("/updateMajor")
    @ResponseBody
    public ServerResponse<String> updateMajor(Integer majorId,String majorNum,String majorName){
        return majorService.updateMajor(majorId,majorNum,majorName);
    }
    @GetMapping("/deleteMajor")
    @ResponseBody
    public ServerResponse<String> deleteMajor(Integer majorId){
        return majorService.deleteMajor(majorId);
    }

    @GetMapping("/findAllMajors")
    @ResponseBody
    public ServerResponse<List<Major>> findAllMajors(){
        return majorService.findAllMajors();
    }
}
