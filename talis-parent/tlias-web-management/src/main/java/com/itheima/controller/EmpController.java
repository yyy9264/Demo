package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParams;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理的Controller
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * @RequestParam 设置默认值
     */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
//        log.info("分页查询, {},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
//        PageResult<Emp> pageResult = empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }
    @GetMapping
    public Result page(EmpQueryParams empQueryParams){
        log.info("分页查询, {}", empQueryParams);
        PageResult<Emp> pageResult = empService.page(empQueryParams);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工, {}", emp);
        empService.save(emp);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("根据ID删除员工，接收参数：{}",ids);
        empService.deleteById(ids);
        return Result.success();
    }
    /*
    修改员工-查询回显
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询员工信息，id:{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }
    /*
        修改员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息，接收参数：{}",emp);
        empService.update(emp);
        return Result.success();
    }
    /*
    查询所有员工信息
     */
    @GetMapping("/list")
    public Result findAll(){
        log.info("查询所有员工信息");
        List<Emp> list = empService.findAll();
        return Result.success(list);
    }
}
