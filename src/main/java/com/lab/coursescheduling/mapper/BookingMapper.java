package com.lab.coursescheduling.mapper;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.Booking;
import com.lab.coursescheduling.bean.po.BookingTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface BookingMapper {
    List<Booking> findAll();

    Boolean deleteBookingInfo(LocalDate date,String classTime,String studentNum);

    List<Booking> findAllOwnBooking(String studentNum);

    BookingTime findOne(LocalDate date,String classTime);

    Boolean addOne(@Param("bookingTime") BookingTime bookingTime);

    Boolean banBookingTime(LocalDate date, String classTime);

    Boolean allowBookingTime(LocalDate date, String classTime);

    Boolean booking(LocalDate date, String classTime,String studentNum);

    Boolean updateBookingTime(LocalDate date, String classTime,Integer flag);

    Booking getBookingInfo(LocalDate date, String classTime, String studentNum);

    Boolean cannelBooking(LocalDate date, String classTime, String studentNum);
    //禁止预约时间时使用，删除booing_info中目标时段的预约信息
    Boolean deleteBookingInfoByDateAndClassTime(LocalDate date, String classTime);
    //查询该时间段是否有预约
    List<Booking> findAllByDateAndClassTime(LocalDate date, String classTime);
}
