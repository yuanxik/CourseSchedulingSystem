package com.lab.coursescheduling.util;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.vo.ConstantVo;
import org.apache.poi.util.ArrayUtil;
import org.thymeleaf.util.ArrayUtils;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class CourseScheduleUtil {


    /*
    随机分配一个不重复的时间片:同一班级
     */
    public static String randomTime(String gene, List<String> geneList) {
        //1~25分别表示一周里5*5=25节课
        String classTime;
        int temp = new Random().nextInt(25) + 1;//生成1到25的随机数
        if (temp < 10) {
            classTime = "0" + temp;
        } else {
            classTime = "" + temp;
        }
        //判断classTime是否重复
        if (isRepeat(classTime, gene, geneList)) {
            return classTime;
        } else {
            return randomTime(gene, geneList);
        }
    }

    /*
    随机分配一个不重复时间片：所有班级
     */
    public static String randomAllTime(String gene, List<String> geneList) {
        //1~25分别表示一周里5*5=25节课
        String classTime;
        int temp = new Random().nextInt(25) + 1;//生成1到25的随机数
        if (temp < 10) {
            classTime = "0" + temp;
        } else {
            classTime = "" + temp;
        }
        //判断classTime是否重复
        if (isRepeatAll(classTime, geneList)) {
            return classTime;
        } else {
            return randomAllTime(gene,geneList);
        }
    }

    /*
    判断时间片是否重复:判断同一个班是否在同一时间内上课有重复
     */
    public static Boolean isRepeat(String classTime, String gene, List<String> geneList) {
        String classNum = cutGene(ConstantVo.CLASS_NUM, gene);
        for (String g : geneList) {
            //判断是否同一班级
            if (classNum.equals(cutGene(ConstantVo.CLASS_NUM, g))) {
                //判断同一班级classTime是否相同
                if (classTime.equals(cutGene(ConstantVo.CLASS_TIME, g))) return false;
            }
        }
        return true;
    }

    /*
    判断时间片是否重复:判断同一时间内上课有重复
     */
    public static Boolean isRepeatAll(String classTime, List<String> geneList) {
        for (String g : geneList) {
            if (classTime.equals(CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, g))) return false;
        }
        return true;

    }

    /*
    根据classTime返回周几
     */
    public static String theDaysCourse(String classTime) {
        String[] monday = {"01", "02", "03", "04", "05"};
        String[] tuesday = {"06", "07", "08", "09", "10"};
        String[] wednesday = {"11", "12", "13", "14", "15"};
        String[] thursday = {"16", "17", "18", "19", "20"};
        String[] friday = {"21", "22", "23", "24", "25"};
        if (ArrayUtils.contains(monday, classTime)) {
            return "monday";
        } else if (ArrayUtils.contains(tuesday, classTime)) {
            return "tuesday";
        } else if (ArrayUtils.contains(wednesday, classTime)) {
            return "wednesday";
        } else if (ArrayUtils.contains(thursday, classTime)) {
            return "thursday";
        } else  {
            return "friday";
        }
    }


    /*
    适应值衡量:根据一天里的上课节数
     */
    public static Integer fitness_dayCourse(Map<String, Integer> courseNumMap){
        int fitness=0;
        for (Integer num:courseNumMap.values()){
            switch (num){
                case 1:
                    fitness+=5;
                    break;
                case 2:
                    fitness+=4;
                    break;
                case 3:
                    fitness+=3;
                    break;
                case 4:
                    fitness+=2;
                    break;
                case 5:
                    fitness+=1;
                    break;
                default:
            }
        }
        return fitness;
    }


    /*
    获取基因片段方法
     */
    public static String cutGene(String aim, String gene) {
        switch (aim) {
            case ConstantVo.EXCEPT_CLASS_TIMT:
                return gene.substring(0, 24);
            case ConstantVo.IS_FIX:
                return gene.substring(0, 1);
            case ConstantVo.MAJOR_NUM:
                return gene.substring(1, 3);
            case ConstantVo.CLASS_NUM:
                return gene.substring(3, 11);
            case ConstantVo.TEACHER_NUM:
                return gene.substring(11, 17);
            case ConstantVo.COURSE_NUM:
                return gene.substring(17, 22);
            case ConstantVo.COURSE_ATTR:
                return gene.substring(22, 24);
            case ConstantVo.CLASS_TIME:
                return gene.substring(24, 26);
            default:
                return "";
        }

    }


}
