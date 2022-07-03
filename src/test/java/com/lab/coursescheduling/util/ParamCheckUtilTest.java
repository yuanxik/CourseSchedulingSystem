package com.lab.coursescheduling.util;

import com.lab.coursescheduling.bean.vo.CheckVo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
    @auther
    @create ---
*/class ParamCheckUtilTest {

    @Test
    void checkParamLegal() {
        Boolean aBoolean = ParamCheckUtil.checkIntegerParamLegal(CheckVo.STUDENT_NUM, "181153021原");
        System.out.println("181153021原".charAt(9));
        System.out.println(aBoolean);
    }
    @Test
    void checkMixParamLegal(){
        Boolean aBoolean = ParamCheckUtil.checkMixParamLegal(6, 10, "nimei11@@");
        System.out.println(aBoolean);
    }

}