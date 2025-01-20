package com.itheima.controller;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;
    /*
        统计员工职位信息
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData()
    {
        log.info("统计员工职位信息");
        JobOption jobOption= reportService.getEmpJobData();
        return Result.success(jobOption);
    }
    /*
        统计员工性别信息
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData()
    {
        log.info("统计员工性别信息");
        List<Map<String,Object>> genderList= reportService.getEmpGenderData();
        return Result.success(genderList);
    }
    /*
    统计班级人数
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData()
    {
        log.info("统计班级人数");
        Map<String, List<? extends Serializable>> list= reportService.getStudentCountData();
        return Result.success(list);
    }
    /*
    统计学生学历
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData()
    {
        log.info("统计学生学历");
        List<Map<String,Object> > list= reportService.getStudentDegreeData();
        return Result.success(list);
    }
}
