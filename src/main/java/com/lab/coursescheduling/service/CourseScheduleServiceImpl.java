package com.lab.coursescheduling.service;/*
    @auther
    @create ---
*/

import com.lab.coursescheduling.bean.po.ClassTask;
import com.lab.coursescheduling.bean.po.CoursePlan;
import com.lab.coursescheduling.bean.vo.ConstantVo;
import com.lab.coursescheduling.mapper.ClassTaskMapper;
import com.lab.coursescheduling.mapper.CoursePlanMapper;
import com.lab.coursescheduling.mapper.CourseScheduleMapper;
import com.lab.coursescheduling.mapper.TeacherCourseMapper;
import com.lab.coursescheduling.util.CourseScheduleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CourseScheduleServiceImpl implements CourseScheduleService {

    private final String UNFIXED_TIME = "unFixedTime";//没有固定时间
    private final String IS_FIXED_TIME = "isFixedTime";//固定时间
    private Integer MAX_POPULATION_FITNESS;//最大种群适应值
    private Map<String, List<String>> RES_POPULATION;

    @Autowired
    private CourseScheduleMapper courseScheduleMapper;
    @Autowired
    private ClassTaskMapper classTaskMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    /*
     排课主方法
     */
    @Transactional
    @Override
    public Boolean classScheduling(String semester) {
        boolean flag = true;
        try {
            //1.取出开课任务
            List<ClassTask> classTaskList = classTaskMapper.selectTaskBySemester(semester);
            //2.将开课任务进行基因编码
            Map<String, List<String>> geneList = coding(classTaskList);
            //3.对编码后的基因分配时间片
            List<String> geneListCodingTime = codingTime(geneList);
            //4.对编码后的基因分类，生成以班级为单位的个体
            Map<String, List<String>> individualMap = getIndividualMap(geneListCodingTime);
            //5.交叉变异
            Map<String, List<String>> resMap = geneCrossoverAndMutation(individualMap);
            //6.解码
            List<CoursePlan> coursePlanList = deCoding(resMap);
            //7.存入数据库
            for (CoursePlan coursePlan : coursePlanList) {
                if (!coursePlanMapper.addCoursePlans(coursePlan)) flag = false;
                if (!teacherCourseMapper.addTeacherCourse(coursePlan)) flag = false;
            }
            //8.把开课学期和上课周数更新进course_plan
            for (ClassTask classTask1 : classTaskList) {
                if (!coursePlanMapper.updateCoursePlan(classTask1)) flag = false;
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /*
    基因编码方法
     gene = classTask.getIsFix() + classTask.getMajorNum() + classTask.getClassNum() +
                            classTask.getTeacherNum() + classTask.getCourseNum() + classTask.getCourseAttr() +
                            classTask.getClassTime();
     gene码长=1+2+8+6+5+2+2=26位
     */
    @Override
    public Map<String, List<String>> coding(List<ClassTask> classTaskList) {
        //geneListMap:要返回的编码后的基因map
        Map<String, List<String>> geneListMap = new HashMap<>();
        //unFixTimeGeneList:放置不固定上课时间课程的基因列表
        List<String> unFixTimeGeneList = new ArrayList<>();
        //isFixTimeGeneList:放置固定上课时间课程的基因列表
        List<String> isFixTimeGeneList = new ArrayList<>();
        //遍历classTask分情况编码
        for (ClassTask classTask : classTaskList) {
            //IsFix=1,上课时间不固定，classTime编码为“00”
            if (classTask.getIsFix().equals("1")) {
                //计算上课次数=周课时数/2
                int size = classTask.getWeeksNumber() / 2;
                //对每次课进行编码
                for (int i = 0; i < size; i++) {
                    String gene = classTask.getIsFix() + classTask.getMajorNum() + classTask.getClassNum() +
                            classTask.getTeacherNum() + classTask.getCourseNum() + classTask.getCourseAttr() +
                            ConstantVo.DEFAULT_CLASSTIME;
                    unFixTimeGeneList.add(gene);
                }
            }
            //IsFix=2,上课时间固定
            if (classTask.getIsFix().equals("2")) {
                int size = classTask.getWeeksNumber() / 2;
                for (int i = 0; i < size; i++) {
                    //i=0,取getClasstime()的0，1位；i=1，取getClasstime()的2，3位
                    String classTime = classTask.getClassTime().substring(i * 2, (i + 1) * 2);
                    String gene = classTask.getIsFix() + classTask.getMajorNum() + classTask.getClassNum() +
                            classTask.getTeacherNum() + classTask.getCourseNum() + classTask.getCourseAttr() +
                            classTime;
                    isFixTimeGeneList.add(gene);
                }
            }
        }
        geneListMap.put(UNFIXED_TIME, unFixTimeGeneList);
        geneListMap.put(IS_FIXED_TIME, isFixTimeGeneList);
        return geneListMap;
    }

    /*
    分配时间方法:得到完整的基因编码
     */
    @Override
    public List<String> codingTime(Map<String, List<String>> geneList) {
        //geneListCodingTime：经过时间分配后的完整的基因编码列表
        List<String> geneListCodingTime = new ArrayList<>();
        List<String> unFixTimeGeneList = geneList.get(UNFIXED_TIME);
        List<String> isFixTimeGeneList = geneList.get(IS_FIXED_TIME);
        //isFixTimeGeneList固定上课时间无需编码
        geneListCodingTime.addAll(isFixTimeGeneList);
        //unFixTimeGeneList中的基因编码随机分配时间片
        for (String gene : unFixTimeGeneList) {
            //获取不重复时间片
            String classTime = CourseScheduleUtil.randomTime(gene, unFixTimeGeneList);
            //给gene赋予classTime
            gene = CourseScheduleUtil.cutGene(ConstantVo.EXCEPT_CLASS_TIMT, gene) + classTime;
            geneListCodingTime.add(gene);
        }
        return geneListCodingTime;
    }

    /*
    生成个体：以班级为单位
     */
    @Override
    public Map<String, List<String>> getIndividualMap(List<String> geneListCodingTime) {
        //要返回的存放个体的map
        HashMap<String, List<String>> individualMap = new HashMap<>();
        //拿到class_task表中的classNum
        List<String> classNumList = classTaskMapper.selectParamByColumn(ConstantVo.CLASS_NUM);
        for (String classNum : classNumList) {
            ArrayList<String> list = new ArrayList<>();
            for (String gene : geneListCodingTime) {
                if (classNum.equals(CourseScheduleUtil.cutGene(ConstantVo.CLASS_NUM, gene))) {
                    list.add(gene);
                }
            }
            individualMap.put(classNum, list);
//            if (list.size()>1){
//                individualMap.put(classNum,list);
//            }
        }
        return individualMap;
    }

    /*
    交叉变异方法入口：完成种群的交叉和变异
     */
    @Override
    public Map<String, List<String>> geneCrossoverAndMutation(Map<String, List<String>> individualMap) {
        //定义交叉变异的次数
        int generation = ConstantVo.GENERATION;
        for (int i = 0; i < generation; i++) {
            //交叉，产生新一代父本
            Map<String, List<String>> individualMapAfterCrossover = geneCrossover(individualMap);
            //重新聚拢基因库
            List<String> geneList = allGene(individualMapAfterCrossover);
            //变异
            List<String> mutationList = mutation(geneList);
            //冲突检测并消除
            List<String> resList = conflictJudgmentAndResolute_1(mutationList);
            resList = conflictJudgmentAndResolute_2(resList);
            //再次按班级生成个体，进行下一次交叉
            individualMap = getIndividualMap(resList);
        }
        return RES_POPULATION;
    }

    /*
    交叉方法入口，完成整个种群的交叉
     */
    @Override
    public Map<String, List<String>> geneCrossover(Map<String, List<String>> individualMap) {
        //交叉后的种群
        Map<String, List<String>> individualMapAfterCrossover = new HashMap<>();
        //种群适应度
        int populationFitness = 0;
        for (String classNum : individualMap.keySet()) {
            List<String> individualList = individualMap.get(classNum);
            List<String> oldIndividualList = individualList;//父
            individualList = crossover(individualList);//子
            //计算并比较父子的适应度值，选择适应度高的进入下一代
            Map<Integer, List<String>> nextGenerrationMap = compareFitness(oldIndividualList, individualList);
            for (Integer fitness : nextGenerrationMap.keySet()) {
                populationFitness += fitness;
                individualMapAfterCrossover.put(classNum, nextGenerrationMap.get(fitness));
            }
        }
        //衡量2.一个老师一天里的上课节数
        List<String> geneList = allGene(individualMapAfterCrossover);
        Map<String, List<String>> daysCoursesMap = getDaysCourses(geneList);
        //得到每个老师每天的上课节数
        Map<String, Map<String, Integer>> teacherCourseInEveryDayMap = teacherCourseInEveryDay(daysCoursesMap);
        Integer fitness_teacher = getFitness_teacher(teacherCourseInEveryDayMap);
        populationFitness += fitness_teacher;
        //和最大种群适应值比较
        if (MAX_POPULATION_FITNESS == null || MAX_POPULATION_FITNESS < populationFitness) {
            MAX_POPULATION_FITNESS = populationFitness;
            RES_POPULATION = individualMapAfterCrossover;
        }
        return individualMapAfterCrossover;
    }

    /*
    个体基因库交叉
     */
    @Override
    public List<String> crossover(List<String> individualList) {
        boolean flag;
        do {
            //随机选择个体的两条基因
            int firstTemp = new Random().nextInt(individualList.size());
            int secondTemp = new Random().nextInt(individualList.size());
            String firstGene = individualList.get(firstTemp);
            String secondGene = individualList.get(secondTemp);
            if (firstGene == secondGene) {
                //选到了同一条，重新选取
                flag = false;
            } else if (CourseScheduleUtil.cutGene(ConstantVo.IS_FIX, firstGene).equals("2") || CourseScheduleUtil.cutGene(ConstantVo.IS_FIX, secondGene).equals("2")) {
                //其中有固定时间的课，不能交叉，重新选取
                flag = false;
            } else {
                //得到基因编码的classTime
                String firstClassTime = CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, firstGene);
                String secondClassTime = CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, secondGene);
                //交换classTime
                firstGene = CourseScheduleUtil.cutGene(ConstantVo.EXCEPT_CLASS_TIMT, firstGene) + secondClassTime;
                secondGene = CourseScheduleUtil.cutGene(ConstantVo.EXCEPT_CLASS_TIMT, secondGene) + firstClassTime;
                //存入基因list
                individualList.remove(firstTemp);
                individualList.add(firstTemp, firstGene);
                individualList.remove(secondTemp);
                individualList.add(secondTemp, secondGene);
                flag = true;
            }
        } while (!flag);
        return individualList;
    }

    /*
    适应度比较，返回适应度比较大的个体
     */
    private Map<Integer, List<String>> compareFitness(List<String> oldIndividualList, List<String> individualList) {
        Integer oldFitness = getFitness_class(oldIndividualList);
        Integer fitness = getFitness_class(individualList);
        HashMap<Integer, List<String>> map = new HashMap<>();
        if (oldFitness >= fitness) {
            map.put(oldFitness, oldIndividualList);
        } else {
            map.put(fitness, individualList);
        }
        return map;
    }

    /*
    计算适应度:
             1.一个班级一天里的上课节数
             2.一个老师一天里的上课节数
     */
    private Integer getFitness_class(List<String> individualList) {
        int fitness = 0;
        //把班级课表以天为单位划分
        Map<String, List<String>> daysCoursesMap = getDaysCourses(individualList);
        //衡量1.一个班级一天里的上课节数
        Map<String, Integer> courseNumMap = courseInOneDay(daysCoursesMap);
        fitness += CourseScheduleUtil.fitness_dayCourse(courseNumMap);

        return fitness;
    }

    /*
    计算适应度2：
     */
    private Integer getFitness_teacher(Map<String, Map<String, Integer>> teacherCourseInEveryDayMap) {
        int fitnessSum = 0;
        for (String week : teacherCourseInEveryDayMap.keySet()) {
            Map<String, Integer> everyMap = teacherCourseInEveryDayMap.get(week);
            Integer fitness = CourseScheduleUtil.fitness_dayCourse(everyMap);
            fitnessSum += fitness;
        }
        return fitnessSum;

    }

    /*
    把课程以天为单位划分
     */
    private Map<String, List<String>> getDaysCourses(List<String> geneList) {
        //存放一个班级每天的课程，key=星期
        Map<String, List<String>> everyDayCourseMap = new HashMap<>();
        for (String gene : geneList) {
            //时间不固定的加入map
            if (CourseScheduleUtil.cutGene(ConstantVo.IS_FIX, gene).equals("1")) {
                String classTime = CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, gene);
                //得到课程在周几
                String day = CourseScheduleUtil.theDaysCourse(classTime);
                List<String> list = everyDayCourseMap.getOrDefault(day, new ArrayList<>());
                list.add(gene);
                everyDayCourseMap.put(day, list);
            }
        }
        return everyDayCourseMap;
    }


    /*
    一个班级一天里的上课节数
     */
    private Map<String, Integer> courseInOneDay(Map<String, List<String>> daysCoursesMap) {
        Map<String, Integer> map = new HashMap<>();
        for (String week : daysCoursesMap.keySet()) {
            int num = daysCoursesMap.get(week).size();
            map.put(week, num);
        }
        return map;
    }

    /*
    每个教师每天里的上课节数
     */
    private Map<String, Map<String, Integer>> teacherCourseInEveryDay(Map<String, List<String>> daysCoursesMap) {
        Map<String, Map<String, Integer>> weeksMap = new HashMap<>();
        for (String week : daysCoursesMap.keySet()) {
            Map<String, Integer> dayMap = new HashMap<>();
            List<String> geneList = daysCoursesMap.get(week);
            for (String gene : geneList) {
                String teacherNum = CourseScheduleUtil.cutGene(ConstantVo.TEACHER_NUM, gene);
                Integer courses = dayMap.getOrDefault(teacherNum, 0);
                dayMap.put(teacherNum, ++courses);
            }
            weeksMap.put(week, dayMap);
        }
        return weeksMap;
    }


    /*
    重新聚拢基因库
     */
    private List<String> allGene(Map<String, List<String>> individualMap) {
        List<String> geneList = new ArrayList<>();
        for (List<String> genel : individualMap.values()) {
            geneList.addAll(genel);
        }
        return geneList;
    }

    /*
    变异方法：
     */
    @Override
    public List<String> mutation(List<String> geneList) {
        int max = geneList.size();
        //变异概率
        double mutationRate = ConstantVo.MUTATION_RATE;
        //变异数量
        int mutationNum;
        if ((mutationNum = (int) (max * mutationRate)) < 1) {
            mutationNum = 1;
        }
        for (int i = 0; i < mutationNum; ) {
            int index = new Random().nextInt(max);//随机数0~（max-1）
            String gene = geneList.get(index);
            if (CourseScheduleUtil.cutGene(ConstantVo.IS_FIX, gene).equals("1")) {
                String classTime = CourseScheduleUtil.randomTime(gene, geneList);
                gene = CourseScheduleUtil.cutGene(ConstantVo.EXCEPT_CLASS_TIMT, gene) + classTime;
                geneList.remove(index);
                geneList.add(index, gene);
                i++;
            }
        }
        return geneList;
    }

    /*
    解码
     */
    private List<CoursePlan> deCoding(Map<String, List<String>> resMap) {
        List<String> geneList = allGene(resMap);
        List<CoursePlan> coursePlanList = new ArrayList<>();
        for (String gene : geneList) {
            CoursePlan coursePlan = new CoursePlan();
            coursePlan.setMajorNum(CourseScheduleUtil.cutGene(ConstantVo.MAJOR_NUM, gene));
            coursePlan.setClassNum(CourseScheduleUtil.cutGene(ConstantVo.CLASS_NUM, gene));
            coursePlan.setCourseNum(CourseScheduleUtil.cutGene(ConstantVo.COURSE_NUM, gene));
            coursePlan.setTeacherNum(CourseScheduleUtil.cutGene(ConstantVo.TEACHER_NUM, gene));
            coursePlan.setClassTime(CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, gene));
            coursePlanList.add(coursePlan);
        }
        return coursePlanList;
    }

    /*
    冲突分析:对课程安排的约束因素有班级，教室，教师。
    一个教室的情况下同一时间多个班级上课（1），同一时间一个教师上多门课（2）、同一时间一个班级上多门课（3）以及一个班级一天里同一课程上多个课时（4）是需要考虑的冲突。
    冲突3在randomTime里已经避免了，因为只有一个教室，解决了冲突1后，冲突2也相应解决
    所以判断并解决冲突1和4即可
     */
    @Override
    public List<String> conflictJudgmentAndResolute_1(List<String> mutationList) {
        //解决冲突1
        int max = mutationList.size();
        for (int i = 0; i < max - 1; i++) {
            String gene = mutationList.get(i);
            for (int j = i + 1; j < max; j++) {
                String g = mutationList.get(j);
                if (CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, gene).equals(CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, g))) {
                    g = CourseScheduleUtil.cutGene(ConstantVo.EXCEPT_CLASS_TIMT, g) + CourseScheduleUtil.randomAllTime(g, mutationList);
                    mutationList.remove(j);
                    mutationList.add(j, g);
                }
            }
        }
        return mutationList;
    }


    /*
    解决冲突四
     */
    @Override
    public List<String> conflictJudgmentAndResolute_2(List<String> mutationList) {
        while (!repeatCourseInOneDay(mutationList)) {
            int size = mutationList.size();
            for (int i = 0; i < size - 1; i++) {
                String gene = mutationList.get(i);
                String classNum = CourseScheduleUtil.cutGene(ConstantVo.CLASS_NUM, gene);
                String courseNum = CourseScheduleUtil.cutGene(ConstantVo.COURSE_NUM, gene);
                for (int j = i + 1; j < size; j++) {
                    String g = mutationList.get(j);
                    String num = CourseScheduleUtil.cutGene(ConstantVo.CLASS_NUM, g);
                    String courNum = CourseScheduleUtil.cutGene(ConstantVo.COURSE_NUM, g);
                    if (classNum.equals(num) && courseNum.equals(courNum)) {
                        String weeks_1 = CourseScheduleUtil.theDaysCourse(CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, gene));
                        String weeks_2 = CourseScheduleUtil.theDaysCourse(CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, g));
                        if (weeks_1.equals(weeks_2)) {
                            List<String> weeksList = getCourseWeeks(gene, mutationList);
                            String classTime = CourseScheduleUtil.randomAllTime(gene, mutationList);
                            while (weeksList.contains(CourseScheduleUtil.theDaysCourse(classTime))) {
                                classTime = CourseScheduleUtil.randomAllTime(gene, mutationList);
                            }
                            g = CourseScheduleUtil.cutGene(ConstantVo.EXCEPT_CLASS_TIMT, g) + classTime;
                            mutationList.remove(j);
                            mutationList.add(j, g);
                        }
                    }
                }
            }
        }
        return mutationList;
    }

    /*
    判断冲突四存在：一个班级一天里同一课程上多个课时
     */
    private Boolean repeatCourseInOneDay(List<String> mutationList) {
        int size = mutationList.size();
        for (int i = 0; i < size - 1; i++) {
            String gene = mutationList.get(i);
            String classNum = CourseScheduleUtil.cutGene(ConstantVo.CLASS_NUM, gene);
            String courseNum = CourseScheduleUtil.cutGene(ConstantVo.COURSE_NUM, gene);
            for (int j = i + 1; j < size; j++) {
                String g = mutationList.get(j);
                String num = CourseScheduleUtil.cutGene(ConstantVo.CLASS_NUM, g);
                String courNum = CourseScheduleUtil.cutGene(ConstantVo.COURSE_NUM, g);
                if (classNum.equals(num) && courseNum.equals(courNum)) {
                    String weeks_1 = CourseScheduleUtil.theDaysCourse(CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, gene));
                    String weeks_2 = CourseScheduleUtil.theDaysCourse(CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, g));
                    if (weeks_1.equals(weeks_2)) return false;
                }
            }
        }
        return true;
    }

    /*
    得到班级某个课程的开课星期
     */
    private List<String> getCourseWeeks(String gene, List<String> mutationList) {
        List<String> list = new ArrayList<>();
        String classNum = CourseScheduleUtil.cutGene(ConstantVo.CLASS_NUM, gene);
        String courseNum = CourseScheduleUtil.cutGene(ConstantVo.COURSE_NUM, gene);
        String weeks_1 = CourseScheduleUtil.theDaysCourse(CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, gene));
        for (String ge : mutationList) {
            String num = CourseScheduleUtil.cutGene(ConstantVo.CLASS_NUM, ge);
            String courNum_2 = CourseScheduleUtil.cutGene(ConstantVo.COURSE_NUM, ge);
            String weeks_2 = CourseScheduleUtil.theDaysCourse(CourseScheduleUtil.cutGene(ConstantVo.CLASS_TIME, ge));
            if (classNum.equals(num) && courseNum.equals(courNum_2)) {
                if (!list.contains(weeks_2)) list.add(weeks_2);
            }

        }
        return list;
    }
}
