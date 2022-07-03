package com.lab.coursescheduling.mapper;

import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.po.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/
@SpringBootTest(classes = CourseSchedulingApplication.class)
class BookingMapperTest {
    @Autowired
    private BookingMapper bookingMapper;

    @Test
    void findAll() {
        List<Booking> all = bookingMapper.findAll();
        for (Booking booking:all){
            System.out.println(booking);
        }
    }

    @Test
    void delete(){

    }

    @Test
    void time(){
        //2022-05-21T11:43:39.727
        LocalDateTime today=LocalDateTime.now();
        System.out.println(today);
        System.out.println(today.getDayOfWeek().getValue());
        //2022-05-21
        LocalDate day=LocalDate.now();
        System.out.println(day);
        System.out.println(day.getDayOfWeek().getValue());
    }
    @Test
    void banBookingTime(){
        LocalDate today=LocalDate.now();
        Boolean aBoolean = bookingMapper.banBookingTime(today.plusDays(2), "2");
        System.out.println(aBoolean);
    }

    @Test
    void booking(){
        Boolean booking = bookingMapper.booking(LocalDate.parse("2022-02-23"), "2", "1811530222");
        System.out.println(booking);
    }
}