package com.lab.coursescheduling.web.admin;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.BookingTime;
import com.lab.coursescheduling.bean.vo.BookingTimeVo;
import com.lab.coursescheduling.bean.vo.BookingVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.CoursePlanMapper;
import com.lab.coursescheduling.service.BookingService;
import com.lab.coursescheduling.service.CoursePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/booking")
public class BookingController {
    @Autowired
    private CoursePlanService coursePlanService;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookingInfo")
    public String bookingInfo(){
        return "administrator/booking_admin";
    }

    @GetMapping("/bookingTimeInfo")
    public String bookingTimeInfo(){
        return "administrator/bookingTime_adminn";
    }

    @GetMapping("/findAllTime")
    @ResponseBody
    public ServerResponse<PageInfo<BookingTimeVo>> toBooking(){
        return bookingService.findBookingTime();
    }

    /*
    分页查询预约信息
     */
    @GetMapping("/findAll")
    @ResponseBody
    public ServerResponse<PageInfo<BookingVo>> findAll(Integer pageNum,Integer pageSize){
        return bookingService.findAll(pageNum,pageSize);
    }
    /*
    删除预约信息
     */
    @GetMapping("/deleteBookingInfo")
    @ResponseBody
    public ServerResponse<String> deleteBookingInfo(String date,String classTime,String studentNum){
        return bookingService.deleteBookingInfo(date,classTime,studentNum);
    }

    /*
    禁止预约时间
     */
    @GetMapping("/banBookingTime")
    @ResponseBody
    public ServerResponse<String> banBookingTime(String date,String classTime){
        return bookingService.banBookingTime(date,classTime);
    }
    /*
    允许预约时间
     */
    @GetMapping("/allowBookingTime")
    @ResponseBody
    public ServerResponse<String> allowBookingTime(String date,String classTime){
        return bookingService.allowBookingTime(date,classTime);
    }


}
