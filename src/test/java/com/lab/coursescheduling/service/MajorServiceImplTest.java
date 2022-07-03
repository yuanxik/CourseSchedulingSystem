package com.lab.coursescheduling.service;

import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.po.Major;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.MajorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/
@SpringBootTest(classes = CourseSchedulingApplication.class)
class MajorServiceImplTest {
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private MajorService majorService;

    @Test
    void addMajor() {
        majorMapper.addMajor(new Major("01","通信工程"));
        majorMapper.addMajor(new Major("02","通信工程（中外合作）"));
    }

    @Test
    void deleteMajor() {
    }

    @Test
    void updateMajor() {
    }

    @Test
    void find(){
        ServerResponse<Major> serverResponse = majorService.find(1);
        System.out.println(serverResponse.getData());
    }
}