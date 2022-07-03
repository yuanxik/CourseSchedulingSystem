package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.ClassTask;

import java.util.List;
import java.util.Map;

public interface CourseScheduleService {

    //排课入口
    Boolean classScheduling(String semester);

    //编码方法
    Map<String, List<String>> coding(List<ClassTask> classTaskList);

    //分配时间片
    List<String> codingTime(Map<String, List<String>> geneList);

    //生成个体
    Map<String, List<String>> getIndividualMap(List<String> geneListCodingTime);

    //交叉变异方法入口：完成种群的交叉和变异
    Map<String, List<String>> geneCrossoverAndMutation(Map<String, List<String>> individualMap);

    //交叉方法入口，完成整个种群的交叉
    Map<String, List<String>> geneCrossover(Map<String, List<String>> individualMap);

    //个体基因库交叉
    List<String> crossover(List<String> individualList);

    //变异方法
    List<String> mutation(List<String> geneList);

    //分析、解决冲突1
    List<String> conflictJudgmentAndResolute_1(List<String> mutationList);

    //分析、解决冲突4
    List<String> conflictJudgmentAndResolute_2(List<String> mutationList);

}
