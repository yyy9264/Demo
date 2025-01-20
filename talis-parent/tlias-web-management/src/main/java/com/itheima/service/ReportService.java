package com.itheima.service;

import com.itheima.pojo.JobOption;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    Map<String, List<? extends Serializable>> getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
