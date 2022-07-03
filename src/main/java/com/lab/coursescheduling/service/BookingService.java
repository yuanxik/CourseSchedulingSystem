package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.BookingTime;
import com.lab.coursescheduling.bean.vo.BookingTimeVo;
import com.lab.coursescheduling.bean.vo.BookingVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    //查看并管理预约时间（管理员使用）
    ServerResponse<PageInfo<BookingTimeVo>> findBookingTime();

    ServerResponse<PageInfo<BookingVo>> findAll(Integer pageNum,Integer pageSize);

    ServerResponse<String> deleteBookingInfo(String date,String classTime,String studentNum);

    ServerResponse<PageInfo<BookingVo>> findAllOwnBooking(HttpSession session,Integer pageNum,Integer pageSize);

    ServerResponse<String> banBookingTime(String date, String classTime);

    ServerResponse<String> allowBookingTime(String date, String classTime);
    //查看可预约时间（学生使用）
    ServerResponse<PageInfo<BookingTimeVo>> findAllowBookingTime();
    //学生预约方法
    ServerResponse<String> booking(String date, String classTime,HttpSession session);
    //学生取消预约方法
    ServerResponse<String> cancelBooking(String date, String classTime,String studentNum);

}
