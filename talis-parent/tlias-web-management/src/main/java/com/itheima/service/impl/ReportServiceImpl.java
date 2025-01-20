package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    /*
     * 统计员工职位数据
     */
    @Override
    public JobOption getEmpJobData() {
        //查询员工职位信息
        List<Map<String,Object>> list= empMapper.countEmpJobData();
        //封装数据
        List JobList= list.stream().map(map -> map.get("pos")).toList();
        List DataList= list.stream().map(map -> map.get("num")).toList();
        return new JobOption(JobList,DataList);
    }
    /*
     * 统计员工性别数据
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        //
        List<Map<String,Object>> genderList= empMapper.countEmpGenderData();
        return genderList;
    }
    /*
     * 统计学生数量数据
     */
    @Override
    public Map<String, List<? extends Serializable>> getStudentCountData() {
        List<Map<String, Object>> list = studentMapper.getStudentCountData();

        List<String> clazzList = list.stream()
                .map(map -> map.get("clazz").toString())
                .toList();

        List<Integer> dataList = list.stream()
                .map(map -> Integer.parseInt(map.get("data").toString()))
                .toList();

        return Map.of("clazzList", clazzList, "dataList", dataList);
    }
    /*
     * 统计学生学历
     */
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }
}
