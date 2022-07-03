package com.lab.coursescheduling.web.admin;/*
    @auther
    @create ---
*/

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.coursescheduling.bean.po.Student;
import com.lab.coursescheduling.service.StudentService;
import com.lab.coursescheduling.util.ImportExcel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;
/*
【注意】excel每个列名要和数据库字段名一致！！
对于有date类型的数据，excel输入2019-12-12会变为日期格式数据，日期类型传到后台时会转成字符串，其格式会出错，就无法转换Date类型，
所以Excel表格一定要将时间相关数据用文本格式存储！！！
数据库对应实体类与时间相关属性要添加@DateTimeFormat(pattern="yyyy-MM-dd")注解
 */
@RestController
public class UploadController {

    @Autowired
    private  StudentService studentService;

    /*
    批量导入excel数据到数据库中
     */
    @PostMapping("/admin/upload")
    public  String upload(@RequestParam("file") MultipartFile file){
        try {
            String fileName = file.getOriginalFilename();
            // 获取上传文件的输入流
            InputStream inputStream = file.getInputStream();
            // 调用工具类中方法，读取excel文件中数据
            List<Map<String, Object>> sourceList = ImportExcel.readExcel(fileName, inputStream);
            // 将对象先转为json格式字符串，然后再转为List<SysUser> 对象
            ObjectMapper objMapper = new ObjectMapper();
            String infos = objMapper.writeValueAsString(sourceList);
            // json字符串转对象
            List<Student> list = objMapper.readValue(infos, new TypeReference<List<Student>>() {});
            // 批量添加
            Boolean aBoolean = studentService.addStusByList(list);
            return "批量添加成功";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
