package com.itheima.service.impl;

import com.itheima.exception.DeleteException;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import com.itheima.service.DeptService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        List<Emp> list = deptMapper.findStudentsByDeptId(id);
        if(list.size()!=0){
            throw new DeleteException("该部门下有员工，不能删除");
        }
        deptMapper.deleteById(id);
    }
    @Override
    public void add(Dept dept) {
        //补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //调用mapper
        deptMapper.insert(dept);
    }
    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }
    @Override
    public void update(Dept dept) {
        //补全基础属性
        dept.setUpdateTime(LocalDateTime.now());
        //调用mapper
        deptMapper.update(dept);
    }
}
