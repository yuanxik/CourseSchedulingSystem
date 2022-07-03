package com.lab.coursescheduling.service;

import com.lab.coursescheduling.CourseSchedulingApplication;
import com.lab.coursescheduling.bean.po.ClassTask;
import com.lab.coursescheduling.bean.vo.ConstantVo;
import com.lab.coursescheduling.mapper.ClassTaskMapper;
import com.lab.coursescheduling.mapper.CourseScheduleMapper;
import com.lab.coursescheduling.mapper.TeacherMapper;
import com.lab.coursescheduling.util.CourseScheduleUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/
@SpringBootTest(classes = CourseSchedulingApplication.class)
class CourseScheduleServiceImplTest {

    @Autowired
    private ClassTaskMapper classTaskMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CourseScheduleService courseScheduleService;

    @Test
    void classScheduling() {
        Boolean aBoolean = courseScheduleService.classScheduling("2018-2019-01");
        System.out.println(aBoolean);
    }

    @Test
    void coding() {
        List<ClassTask> classTasks = classTaskMapper.selectTaskBySemester("2018-2019-01");
        Map<String, List<String>> coding = courseScheduleService.coding(classTasks);
        for (List<String> list : coding.values()) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println("*********");
        }
    }

    @Test
    void codingTime() {
        List<ClassTask> classTasks = classTaskMapper.selectTaskBySemester("2018-2019-01");
        Map<String, List<String>> coding = courseScheduleService.coding(classTasks);
        List<String> list = courseScheduleService.codingTime(coding);
        System.out.println(list.size());
        for (String s : list) {
            System.out.println("班级：" + CourseScheduleUtil.cutGene(ConstantVo.CLASS_NUM, s) + "时间：" + CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, s));
        }
    }

    @Test
    void getIndividualMap() {
        List<ClassTask> classTasks = classTaskMapper.selectTaskBySemester("2018-2019-01");
        Map<String, List<String>> coding = courseScheduleService.coding(classTasks);
        List<String> list = courseScheduleService.codingTime(coding);
        Map<String, List<String>> individualMap = courseScheduleService.getIndividualMap(list);
        for (String classNum : individualMap.keySet()) {
            System.out.println("班级：" + classNum);
            List<String> geneList = individualMap.get(classNum);
            for (String gene : geneList) {
                System.out.println(gene);
            }
        }
    }

    @Test
    void geneCrossoverAndMutation() {

    }

    @Test
    void crossover() {
        List<ClassTask> classTasks = classTaskMapper.selectTaskBySemester("2018-2019-01");
        Map<String, List<String>> coding = courseScheduleService.coding(classTasks);
        List<String> list = courseScheduleService.codingTime(coding);
        Map<String, List<String>> individualMap = courseScheduleService.getIndividualMap(list);
        List<String> oldGeneList = individualMap.get("20180101");
        for (String s : oldGeneList) System.out.println(s);
        System.out.println("**********************");
        List<String> geneList = courseScheduleService.crossover(oldGeneList);
        for (String s : geneList) System.out.println(s);
    }

    @Test
    void mutation() {
        List<ClassTask> classTasks = classTaskMapper.selectTaskBySemester("2018-2019-01");
        Map<String, List<String>> coding = courseScheduleService.coding(classTasks);
        List<String> list = courseScheduleService.codingTime(coding);
        Map<String, List<String>> individualMap = courseScheduleService.getIndividualMap(list);
        List<String> geneList = individualMap.get("20180101");
        List<String> mutationList = courseScheduleService.mutation(geneList);
        for (String s : geneList) System.out.println(s);
        System.out.println("****************");
        for (String s : mutationList) System.out.println(s);

    }

    @Test
    void conflictJudgmentAndResolute() {
        String gene_1 = "10120180101000001100010119";
        String gene_2 = "10120180102000001100020119";
        String gene_3 = "10120180103000001100030118";
        String gene_4 = "10120180104000001100040118";
        String gene_5 = "10120180201000001100010120";
        String gene_6 = "10120180202000001100020118";
        String gene_7 = "10120180203000001100030120";
        String gene_8 = "10120180204000001100040101";
        String gene_9 = "10120180101000001100010118";
        String gene_10 = "10120180101000001100010101";
        String gene_11 = "10120180101000001100010103";
        String gene_12 = "10120180101000001100010103";
        ArrayList<String> list = new ArrayList<>();
        list.add(gene_1);
        list.add(gene_2);
        list.add(gene_3);
        list.add(gene_4);
        list.add(gene_5);
        list.add(gene_6);
        list.add(gene_7);
        list.add(gene_8);
        list.add(gene_9);
        list.add(gene_10);
        list.add(gene_11);
        list.add(gene_12);
        List<String> resolute = courseScheduleService.conflictJudgmentAndResolute_1(list);
        for (String gene:resolute) System.out.println(gene);
        ArrayList<String> list1 = new ArrayList<>();
        for (String s:resolute){
            String classTime=CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME,s);
            if (!list1.contains(classTime)) list1.add(classTime);
            System.out.print(classTime+" ");
        }
        System.out.println(list1.size());

    }

    @Test
    void conflictJudgmentAndResolute_2() {
        String gene_1 = "10120180101000001100010101";
        String gene_2 = "10120180101000001100010106";
        String gene_3 = "10120180101000001100010111";
        String gene_4 = "10120180101000001100010116";
        String gene_5 = "10120180101000001100010117";
//        String gene_6 = "10120180101000001100010121";
        ArrayList<String> list = new ArrayList<>();
        list.add(gene_1);
        list.add(gene_2);
        list.add(gene_3);
        list.add(gene_4);
        list.add(gene_5);
//        list.add(gene_6);
        List<String> methodList = courseScheduleService.conflictJudgmentAndResolute_2(list);
        for (String s : methodList) System.out.println(s);
    }
}