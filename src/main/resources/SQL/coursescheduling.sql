use
coursescheduling;

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;


--  用户表
DROP TABLE IF EXISTS `user_info`;
create table `user_info`
(
    `id`            bigint(10) not null auto_increment,
    `user_name`     varchar(10) not null unique ,
    `user_password` varchar(10) not null,
    `role`          varchar(2)  not null default '0',
    PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

--  用户表记录
INSERT INTO `user_info`
VALUES (1, '123456', '123456', '0');
INSERT INTO `user_info`
VALUES (2, '000001', '123456', '0');
INSERT INTO `user_info`
VALUES (3, '100001', '123456', '0');
INSERT INTO `user_info`
VALUES (4, '100002', '123456', '0');
INSERT INTO `user_info`
VALUES (5, '100003', '123456', '0');
INSERT INTO `user_info`
VALUES (6, '100004', '123456', '0');

--  学生表
drop table if exists `student_info`;
create table `student_info`
(
    `student_id`       int (10) not null auto_increment,
    `student_num`      varchar(10) not null unique,
    `student_name`     varchar(10),
    `student_password` varchar(10) not null,
    `role`             varchar(2)  not null default '2',
    `class_num`        varchar(10),
    `major_num`        varchar(10),
    primary key (`student_id`) using btree
)ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

--  学生表记录
insert into `student_info`
values (1, '1811530222', '原希1', '123456', '2', '20180101', '01');
insert into `student_info`
values (2, '1811530223', '原希2', '123456', '2', '20180102', '01');
insert into `student_info`
values (3, '1811530224', '原希3', '123456', '2', '20180103', '01');
insert into `student_info`
values (4, '1811530225', '原希4', '123456', '2', '20180104', '01');
insert into `student_info`
values (5, '1811530226', '原希5', '123456', '2', '20180201', '02');
insert into `student_info`
values (6, '1811530227', '原希6', '123456', '2', '20180202', '02');
insert into `student_info`
values (7, '1811530228', '原希7', '123456', '2', '20180203', '02');
insert into `student_info`
values (8, '1811530229', '原希8', '123456', '2', '20180204', '02');

--  教师表
drop table if exists `teacher_info`;
create table `teacher_info`
(
    `teacher_id`       int(10) not null auto_increment,
    `teacher_num`      varchar(10) not null unique,
    `teacher_name`     varchar(10),
    `teacher_password` varchar(10) not null,
    `role`             varchar(2)  not null default '1',
    primary key (`teacher_id`) using btree
)ENGINE = InnoDB  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

--  教师记录
insert into `teacher_info`
values (1, '100001', '张三1', '123456', '1');
insert into `teacher_info`
values (2, '100002', '张三2', '123456', '1');
insert into `teacher_info`
values (3, '100003', '张三3', '123456', '1');
insert into `teacher_info`
values (4, '100004', '张三4', '123456', '1');
insert into `teacher_info`
values (5, '100005', '张三5', '123456', '1');
insert into `teacher_info`
values (6, '100006', '张三6', '123456', '1');
insert into `teacher_info`
values (7, '100007', '张三7', '123456', '1');

-- 专业表
drop table if exists `major_info`;
create table `major_info`
(
    `major_id`   int(10) not null auto_increment,
    `major_num`  varchar(10) not null unique,
    `major_name` varchar(10) not null,
    primary key (`major_id`) using BTREE
)ENGINE = InnoDB  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 专业记录
insert into `major_info`
values (1, '01', '通信工程');
insert into `major_info`
values (2, '02', '通信工程中外');

-- 班级表
drop table if exists `class_info`;
create table `class_info`
(
    `class_id`   int(10) not null auto_increment,
    `class_num`  varchar(10) not null unique,
    `class_name` varchar(10) not null,
    `major_num`  varchar(10),
    primary key (`class_id`) using BTREE
)ENGINE = InnoDB  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 班级记录
insert into `class_info`
values (1, '20180101', '通信1801', '01');
insert into `class_info`
values (2, '20180102', '通信1802', '01');
insert into `class_info`
values (3, '20180103', '通信1803', '01');
insert into `class_info`
values (4, '20180104', '通信1804', '01');
insert into `class_info`
values (5, '20180201', '通信h1801', '02');
insert into `class_info`
values (6, '20180202', '通信h1802', '02');
insert into `class_info`
values (7, '20180203', '通信h1803', '02');
insert into `class_info`
values (8, '20180204', '通信h1804', '02');

-- 课程信息表
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info`
(
    `id`          bigint(10) NOT NULL AUTO_INCREMENT,
    `course_num`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL unique ,
    `course_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `course_attr` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 课程记录
insert into `course_info`
values (1, '10001', '现代交换技术', '01');
insert into `course_info`
values (2, '10002', 'ngn软交换', '01');
insert into `course_info`
values (3, '10003', '现代通信原理', '02');
insert into `course_info`
values (4, '10004', '数据结构', '02');

-- 课程安排表
DROP TABLE IF EXISTS `course_plan`;
CREATE TABLE `course_plan`
(
    `id`          bigint(10) NOT NULL AUTO_INCREMENT,
    `major_num`   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `class_num`   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `course_num`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `teacher_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `classTime`   varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `weeksSum`    int(4) NULL DEFAULT NULL,
    `weekStart`   int(4) null default null,
    `weekEnd`     int(4) null default null,
    `semester`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 779 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 课程安排记录


-- 班级任务
DROP TABLE IF EXISTS `class_task`;
CREATE TABLE `class_task`
(
    `id`            bigint(10) NOT NULL AUTO_INCREMENT,
    `semester`      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `major_num`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `class_num`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `course_num`    varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `teacher_num`   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `courseAttr`    varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `studentNumber` int(4) NULL DEFAULT NULL,
    `weeksSum`      int(4) NULL DEFAULT NULL,
    `weeksNumber`   int(4) NULL DEFAULT NULL,
    `classTime`     varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `isFix`         varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 班级任务记录
insert into `class_task` values (null, '2018-2019-01', '01', '20180101', '10001', '100001', '01', 30, 6, 4, null, '1');
# insert into `class_task` values (null, '2018-2019-01', '01', '20180101', '10002', '000002', '01', 30, 6, 4, null, '1');
insert into `class_task` values (null, '2018-2019-01', '01', '20180102', '10001', '100001', '01', 30, 6, 4, null, '1');
# insert into `class_task` values (null, '2018-2019-01', '01', '20180102', '10002', '000002', '01', 30, 6, 4, null, '1');
insert into `class_task` values (null, '2018-2019-01', '01', '20180103', '10001', '100003', '01', 30, 6, 4, null, '1');
# insert into `class_task` values (null, '2018-2019-01', '01', '20180103', '10003', '000004', '01', 30, 6, 4, null, '1');
insert into `class_task` values (null, '2018-2019-01', '01', '20180104', '10002', '100002', '01', 30, 6, 4, null, '1');
# insert into `class_task` values (null, '2018-2019-01', '01', '20180104', '10003', '000004', '01', 30, 6, 4, null, '1');
insert into `class_task` values (null, '2018-2019-01', '01', '20180201', '10001', '100005', '01', 30, 6, 4, null, '1');
# insert into `class_task` values (null, '2018-2019-01', '01', '20180201', '10002', '000006', '01', 30, 6, 4, null, '1');
insert into `class_task` values (null, '2018-2019-01', '01', '20180202', '10001', '100005', '01', 30, 6, 4, null, '1');
# insert into `class_task` values (null, '2018-2019-01', '01', '20180202', '10003', '000007', '01', 30, 6, 4, null, '1');



--  教学计划
DROP TABLE IF EXISTS `teach_plan`;
CREATE TABLE `teach_plan`
(
    `id`            bigint(10) NOT NULL AUTO_INCREMENT,
    `class_num`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `class_name`    varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `course_num`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `college_num`   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `studentNumber` int(4) NULL DEFAULT NULL,
    `courseAttr`    varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `classHour`     int(4) NULL DEFAULT NULL,
    `weeksNumber`   int(4) NULL DEFAULT NULL,
    `weeksSum`      int(4) NULL DEFAULT NULL,
    `semester`      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 教学计划记录
INSERT INTO `teach_plan`
VALUES (1, '20180101', '计算机科学与技术1班', '100001', '01', 48, '01', 20, 2, 10, '2018-2019-01');

--
DROP TABLE IF EXISTS `teacher_course`;
CREATE TABLE `teacher_course`
(
    `id`          bigint(10) NOT NULL AUTO_INCREMENT,
    `teacher_num`   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `course_num`    varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `class_num`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `classTime`   varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 650 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 预约记录表
Drop table if exists `booking_info`;
create table `booking_info`
(
    `id`          bigint(10) NOT NULL AUTO_INCREMENT,
    `student_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci not NULL ,
    `classTime`   varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci not NULL ,
    `date` datetime(0)  not NULL,
    PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 650 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

--


-- 预约时间表
Drop table if exists `bookingTime_info`;
create table `bookingTime_info`
(
    `id` bigint(10) NOT NULL AUTO_INCREMENT,
    `date` datetime(0)  not NULL,
    `classTime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  not NULL ,
    `studentNumber` bigint(10) not null,
    `isAllow` tinyint(1) default 1,
    PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 650 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

set
FOREIGN_KEY_CHECKS = 1;