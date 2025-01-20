package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParams;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     */
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    PageResult<Emp> page(EmpQueryParams empQueryParams);

    void save(Emp emp);
    //根据id删除员工
    void deleteById(List<Integer> ids);
    //根据id查询员工
    Emp getInfo(Integer id);
    //修改员工
    void update(Emp emp);

    List<Emp> findAll();

    List findByDeptId(Integer id);

    LoginInfo login(Emp emp);
}
