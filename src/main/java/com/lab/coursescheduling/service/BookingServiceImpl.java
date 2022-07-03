package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.coursescheduling.bean.po.Booking;
import com.lab.coursescheduling.bean.po.BookingTime;
import com.lab.coursescheduling.bean.po.Student;
import com.lab.coursescheduling.bean.vo.BookingTimeVo;
import com.lab.coursescheduling.bean.vo.BookingVo;
import com.lab.coursescheduling.bean.vo.ConstantVo;
import com.lab.coursescheduling.bean.vo.ServerResponse;
import com.lab.coursescheduling.mapper.BookingMapper;
import com.lab.coursescheduling.mapper.CoursePlanMapper;
import com.lab.coursescheduling.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public ServerResponse<PageInfo<BookingTimeVo>> findBookingTime() {
        //课程表中的classTime
        List<String> allClassTime = coursePlanMapper.findAllClassTime();
        List<Integer> array = new ArrayList<>();
        for (String s : allClassTime) {
            array.add(Integer.parseInt(s));
        }
        //未被课程占用的时间，被按星期分类
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= ConstantVo.TIME_COUNT; i++) {
            if (array.contains(i)) continue;
            //根据classTime判断周几
            int week = (i - 1) / 5 + 1;
            List<Integer> list = map.getOrDefault(week, new ArrayList<>());
            list.add((i-1)%5+1);
            map.put(week, list);
        }
        //
        List<BookingTimeVo> bookingTimeList=new ArrayList<>();
        //得到当天的时间
        LocalDate today = LocalDate.now();
        int num = 0;
        do {
            int weekNum = today.getDayOfWeek().getValue();
            //周六日不可预约
            if (weekNum == 6 || weekNum == 7){
                today=today.plusDays(1);
                num++;
                continue;
            }
            //找到当天的可预约时间
            List<Integer> timeList = map.get(weekNum);
            for (Integer time:timeList){
                BookingTime bookingTime = bookingMapper.findOne(today, Integer.toString(time));
                BookingTimeVo bookingTimeVo = new BookingTimeVo(bookingTimeList.size()+1,today,getClassTimeToString(time),null,null);
                if (bookingTime==null){
                    bookingMapper.addOne(new BookingTime(null,today,Integer.toString(time),0,true));
                    bookingTimeVo.setStudentNumber(0);
                    bookingTimeVo.setIsAllow(BookingTimeVo.Yes);
                }else {
                    bookingTimeVo.setStudentNumber(bookingTime.getStudentNumber());
                    if (bookingTime.getAllow()){
                        bookingTimeVo.setIsAllow(BookingTimeVo.Yes);
                    }else {
                        bookingTimeVo.setIsAllow(BookingTimeVo.No);
                    }
                }
                bookingTimeList.add(bookingTimeVo);
            }
            today=today.plusDays(1);
            num++;
        } while (num < 7);
        PageInfo<BookingTimeVo> pageInfo=new PageInfo<>(bookingTimeList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo<BookingVo>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Booking> bookingList = bookingMapper.findAll();
        ArrayList<BookingVo> bookingVoList = new ArrayList<>();
        for (Booking booking : bookingList) {
            Student student = studentMapper.findByNum(booking.getStudentNum());
            BookingVo bookingVo = new BookingVo(booking.getId(), student.getStudentNum(), student.getStudentName(), booking.getDate(),getClassTimeToString(Integer.parseInt(booking.getClassTime())) );
            bookingVoList.add(bookingVo);
        }
        PageInfo<BookingVo> pageInfo = new PageInfo<>(bookingVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<String> deleteBookingInfo(String date,String classTime,String studentNum) {
        Boolean aBoolean = bookingMapper.deleteBookingInfo(LocalDate.parse(date),getStringToClassTime(classTime),studentNum);
        Boolean bBoolean=bookingMapper.updateBookingTime(LocalDate.parse(date),getStringToClassTime(classTime),0);
        if (aBoolean&&bBoolean) return ServerResponse.createBySuccessMessage("删除成功");
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<PageInfo<BookingVo>> findAllOwnBooking(HttpSession session, Integer pageNum, Integer pageSize) {
        Student stu = (Student) session.getAttribute("stu");
        PageHelper.startPage(pageNum, pageSize);
        List<Booking> bookingList = bookingMapper.findAllOwnBooking(stu.getStudentNum());
        List<BookingVo> bookingVoList = new ArrayList<>();
        for (Booking booking : bookingList) {
            bookingVoList.add(new BookingVo(booking.getId(), booking.getStudentNum(), stu.getStudentName(), booking.getDate(), getClassTimeToString(Integer.parseInt(booking.getClassTime()))));
        }
        PageInfo<BookingVo> pageInfo = new PageInfo<>(bookingVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<String> banBookingTime(String date, String classTime) {
        Boolean aBoolean=bookingMapper.banBookingTime(LocalDate.parse(date),getStringToClassTime(classTime));
        List<Booking> bookingList = bookingMapper.findAllByDateAndClassTime(LocalDate.parse(date), getStringToClassTime(classTime));
        if (bookingList.size()!=0){
            Boolean bBoolean= bookingMapper.deleteBookingInfoByDateAndClassTime(LocalDate.parse(date),getStringToClassTime(classTime));
            if (!bBoolean) return ServerResponse.createByErrorMessage("操作失败");
        }
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("操作成功");
        }
        return ServerResponse.createByErrorMessage("操作失败");
    }

    @Override
    public ServerResponse<String> allowBookingTime(String date, String classTime) {
        Boolean aBoolean=bookingMapper.allowBookingTime(LocalDate.parse(date),getStringToClassTime(classTime));
        if (aBoolean){
            return ServerResponse.createBySuccessMessage("操作成功");
        }
        return ServerResponse.createByErrorMessage("操作失败");
    }

    @Override
    public ServerResponse<PageInfo<BookingTimeVo>> findAllowBookingTime() {
        //课程表中的classTime
        List<String> allClassTime = coursePlanMapper.findAllClassTime();
        List<Integer> array = new ArrayList<>();
        for (String s : allClassTime) {
            array.add(Integer.parseInt(s));
        }
        //未被课程占用的时间，被按星期分类
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= ConstantVo.TIME_COUNT; i++) {
            if (array.contains(i)) continue;
            //根据classTime判断周几
            int week = (i - 1) / 5 + 1;
            List<Integer> list = map.getOrDefault(week, new ArrayList<>());
            list.add((i-1)%5+1);
            map.put(week, list);
        }
        //
        List<BookingTimeVo> bookingTimeList=new ArrayList<>();
        //得到当天的时间
        LocalDate today = LocalDate.now();
        int num = 0;
        do {
            int weekNum = today.getDayOfWeek().getValue();
            //周六日不可预约
            if (weekNum == 6 || weekNum == 7){
                today=today.plusDays(1);
                num++;
                continue;
            }
            //找到当天的可预约时间
            List<Integer> timeList = map.get(weekNum);
            for (Integer time:timeList){
                BookingTime bookingTime = bookingMapper.findOne(today, Integer.toString(time));
                BookingTimeVo bookingTimeVo = new BookingTimeVo(bookingTimeList.size()+1,today,getClassTimeToString(time),null,null);
                if (bookingTime==null){
                    bookingMapper.addOne(new BookingTime(null,today,Integer.toString(time),0,true));
                    bookingTimeVo.setStudentNumber(0);
                    bookingTimeVo.setIsAllow(BookingTimeVo.Yes);
                }else {
                    if (bookingTime.getAllow()){
                        bookingTimeVo.setStudentNumber(bookingTime.getStudentNumber());
                    }else {
                        continue;
                    }
                }
                bookingTimeList.add(bookingTimeVo);
            }
            today=today.plusDays(1);
            num++;
        } while (num < 7);
        PageInfo<BookingTimeVo> pageInfo=new PageInfo<>(bookingTimeList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<String> booking(String date, String classTime,HttpSession session) {
        Student stu=(Student) session.getAttribute("stu");
        //判断是否重复
        Booking booking= bookingMapper.getBookingInfo(LocalDate.parse(date),getStringToClassTime(classTime),stu.getStudentNum());
        if (booking!=null){
            return ServerResponse.createByErrorMessage("已预约，请勿重复预约");
        }
        //判断该时间预约人数是否已达上限
        BookingTime bookingTime = bookingMapper.findOne(LocalDate.parse(date), getStringToClassTime(classTime));
        if (bookingTime.getStudentNumber()>=BookingTimeVo.STUDENT_MAX) return ServerResponse.createByErrorMessage("该时段预约人数已达上限");
        //加入booking_info
        Boolean aBoolean=bookingMapper.booking(LocalDate.parse(date),getStringToClassTime(classTime), stu.getStudentNum());
        //更新bookingTime_info
        Boolean bBoolean= bookingMapper.updateBookingTime(LocalDate.parse(date),getStringToClassTime(classTime),1);
        if (aBoolean && bBoolean){
            return ServerResponse.createBySuccessMessage("预约成功");
        }
        return ServerResponse.createByErrorMessage("预约失败");
    }

    @Override
    public ServerResponse<String> cancelBooking(String date, String classTime, String studentNum) {
        Boolean aBoolean=bookingMapper.cannelBooking(LocalDate.parse(date),getStringToClassTime(classTime),studentNum);
        Boolean bBoolean=bookingMapper.updateBookingTime(LocalDate.parse(date),getStringToClassTime(classTime),0);
        if (aBoolean&&bBoolean){
            return ServerResponse.createBySuccessMessage("取消预约成功");
        }
        return ServerResponse.createByErrorMessage("取消预约失败");
    }

    public String getClassTimeToString(Integer classTime){
        switch (classTime){
            case 1:
                return "第一大节";
            case 2:
                return "第二大节";
            case 3:
                return "第三大节";
            case 4:
                return "第四大节";
            case 5:
                return "第五大节";
            default:
                return "";
        }
    }

    public String getStringToClassTime(String s){
        switch (s){
            case "第一大节":
                return "1";
            case "第二大节":
                return "2";
            case "第三大节":
                return "3";
            case "第四大节":
                return "4";
            case "第五大节":
                return "5";
            default:
                return "";
        }
    }
}
