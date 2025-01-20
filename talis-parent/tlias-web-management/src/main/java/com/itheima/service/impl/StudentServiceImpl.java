package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    /*
    学生信息分页查询
     */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        // 设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        // 执行查询
        List<Student> studentList=studentMapper.list(studentQueryParam);
        // 获取分页信息
        PageInfo<Student> pageInfo = new PageInfo<>(studentList);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
    /*
    保存学生信息
     */
    @Override
    public void save(Student student) {
        //补全基本属性
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }
    /*
    根据id查询学生信息
     */
    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }
    /*
    更新学生信息
     */
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }
    /*
    删除学生信息
     */
    @Override
    public void deleteById(List<Integer> ids) {
        studentMapper.deleteById(ids);
    }
    /*
    违纪处理
     */
    @Override
    public void violation(Integer id, Integer score) {
        //1.根据id查询学生信息
        Student student = studentMapper.getById(id);
        //2.设置违纪次数和违纪分数
        student.setViolationCount(student.getViolationCount() + 1);
        student.setViolationScore(student.getViolationScore() + score);
        //3.更新学生信息
        studentMapper.update(student);
    }
}
