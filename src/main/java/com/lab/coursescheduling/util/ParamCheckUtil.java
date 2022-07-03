package com.lab.coursescheduling.util;/*
    @auther
    @create ---
*/

public class ParamCheckUtil {
    /*
    非空验证
     */
    public static Boolean checkEmpty(String param){
        if (param==null||param.length()==0) return false;
        return true;
    }

    /*
    检查数字编号如学号，教师编号，班级编号，专业编号等是否合法
     */
    public static Boolean checkIntegerParamLegal(Integer length, String param) {
        if (param == null||param.length()==0) {
            return false;
        } else if (param.length() != length) {
            return false;
        } else {
            for (int i = 0; i < param.length(); i++) {
                char c = param.charAt(i);
                if (c < 48 || c > 57) return false;
            }
        }
        return true;
    }

    /*
    检查数字编号如学号，教师编号，班级编号，专业编号等是否合法,不限制长度
     */
    public static Boolean checkIntegerParamLegal(String param) {
        if (param == null||param.length()==0) {
            return false;
        } else {
            for (int i = 0; i < param.length(); i++) {
                char c = param.charAt(i);
                if (c < 48 || c > 57) return false;
            }
        }
        return true;
    }

    /*
    检查数字字母以及特殊符号的混合参数如密码是否合法
    数字：48-57
    字母：65-90A-Z 97-122 a-z
    特殊字符@64、#35、$36、%37、^94、&38、*42
     */
    public static Boolean checkMixParamLegal(Integer minLength, Integer maxLength, String param) {
        int len;
        if (param == null) {
            return false;
        } else if ((len = param.length()) < minLength || len > maxLength) {
            return false;
        } else {
            for (int i = 0; i < len; i++) {
                char c = param.charAt(i);
                if (c >= 48 && c <= 57) continue;
                if ((c>=65&&c<=90)||(c>=97&&c<=122)) continue;
                if (c==64||(c>=35&&c<=38)||c==94||c==42) continue;
                return false;
            }
        }
        return true;
    }

}
