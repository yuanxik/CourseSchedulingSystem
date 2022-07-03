package com.lab.coursescheduling.service;

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.po.BookingTime;
import com.lab.coursescheduling.bean.vo.BookingTimeVo;
import com.lab.coursescheduling.bean.vo.BookingVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/
@SpringBootTest(classes = CourseSchedulingApplication.class)
class BookingServiceImplTest {

    @Autowired
    private BookingService bookingService;
    @Test
    void findAll() {
        ServerResponse<PageInfo<BookingVo>> all = bookingService.findAll(1, 5);
        for (BookingVo bookingVo:all.getData().getList()){
            System.out.println(bookingVo);
        }
    }
    @Test
    void findBookingTime(){
        ServerResponse<PageInfo<BookingTimeVo>> serverResponse = bookingService.findBookingTime();
        for (BookingTimeVo bookingTimeVo:serverResponse.getData().getList()){
            System.out.println(bookingTimeVo);
        }
        LocalDate today=LocalDate.now();
        System.out.println(today.plusDays(1));
    }
}