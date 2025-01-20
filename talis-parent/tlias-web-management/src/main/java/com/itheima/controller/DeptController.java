package com.itheima.controller;

import com.itheima.anno.LogOperation;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    //查询所有部门数据
    @GetMapping
    public Result findAll(){
        log.info("查询所有部门数据");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }
    //根据id删除部门
    @LogOperation
    @DeleteMapping
    public Result deleteById(@RequestParam("id") Integer id){
        log.info("根据id删除部门，接收参数：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }
    //新增部门
    @LogOperation
    @PostMapping
    public Result add(@RequestBody Dept dept){
        System.out.println("新增部门，接收参数："+dept);
        deptService.add(dept);
        return Result.success();
    }
    //根据id查询部门
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("根据id查询部门，接收参数：{}",id);
        Dept dept=deptService.findById(id);
        return Result.success(dept);
    }
    //修改部门
    @LogOperation
    @PutMapping
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门，接收参数："+dept);
        deptService.update(dept);
        return Result.success();
    }
}
