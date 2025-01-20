package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogServiceImpl empLogService;
    @Override
    public PageResult<Emp> page(EmpQueryParams empQueryParams) {
        //------------------分页查询原始查询方法--------------------------
        //1. 获取总记录数 total
//        Long total = empMapper.count();
//
//        //2. 获取分页查询结果列表 rows
//        //2.1 计算起始索引
//        Integer start = (page - 1) * pageSize;
//        //2.2 执行查询
//        List<Emp> empList = empMapper.list(start, pageSize);

        //3. 封装分页结果

//        return new PageResult<>(total, empList);
//        -----------------PageHelper封装分页查询方法--------------------------
        //调用PaperHelper分页查询方法
        PageHelper.startPage(empQueryParams.getPage(), empQueryParams.getPageSize());
        //调用EmpMapper查询所有员工
        List<Emp> empList = empMapper.list(empQueryParams);
        //获取分页查询结果, 转为Page<Emp>,Page<Emp>继承了List<Emp>
        Page<Emp> p = (Page<Emp>) empList;
        //封装分页结果
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    //开启一个事务,对该方法进行事务管理
    @Transactional(rollbackFor = {Exception.class})//捕获除运行时异常之外的所有异常
    @Override
    public void save(Emp emp) {
        try {
            //补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            //保存员工数据
            empMapper.insert(emp);
          //   int i = 1 / 0;

            //批量保存员工工作经历
            //遍历工作经历列表,并将其empId设置为当前员工id
            emp.getExprList().forEach(expr -> {
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(emp.getExprList());
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工: " + emp);
            empLogService.insertLog(empLog);
        }
    }
    /*
    删除员工
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteById(List<Integer> ids) {
        //删除员工数据
        empMapper.deleteByIds(ids);
        //删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }
    /*
    根据id查询员工信息
     */
    @Override
    public Emp getInfo(Integer id) {
        //查询员工数据
        Emp emp = empMapper.getById(id);
        return emp;
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //根据id更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        //修改员工基本信息
        empMapper.updateById(emp);
        //修改员工工作经历
        //删除员工工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //新增员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }
    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }
    @Override
    public List findByDeptId(Integer id) {
        return empMapper.findByDeptId(id);
    }
    /*
    判断登录是否成功
     */
    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        //创建自定义信息
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",e.getId());
        claims.put("username",e.getUsername());
        //生成token
        String token = JwtUtils.generateJwt(claims);
        if(e!=null){
            return new LoginInfo(e.getId(),e.getUsername(),e.getPassword(),token);
        }
        else return null;
    }

//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //------------------分页查询原始查询方法--------------------------
//        //1. 获取总记录数 total
////        Long total = empMapper.count();
////
////        //2. 获取分页查询结果列表 rows
////        //2.1 计算起始索引
////        Integer start = (page - 1) * pageSize;
////        //2.2 执行查询
////        List<Emp> empList = empMapper.list(start, pageSize);
//
//        //3. 封装分页结果
//
////        return new PageResult<>(total, empList);
////        -----------------PageHelper封装分页查询方法--------------------------
//        //调用PaperHelper分页查询方法
//        PageHelper.startPage(page, pageSize);
//        //调用EmpMapper查询所有员工
//        List<Emp> empList = empMapper.list(name,gender,begin,end);
//        //获取分页查询结果, 转为Page<Emp>,Page<Emp>继承了List<Emp>
//        Page<Emp> p = (Page<Emp>) empList;
//        //封装分页结果
//        return new PageResult<>(p.getTotal(), p.getResult());
//    }

}
