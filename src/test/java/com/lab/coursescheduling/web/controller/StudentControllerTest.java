package com.lab.coursescheduling.web.controller;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/class StudentControllerTest {

    @Test
    void listStus() throws IOException {
        String  resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //sqlsessionFactory创建
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //开启sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();


    }
}