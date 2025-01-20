package com.itheima.controller;

import com.github.pagehelper.Page;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    /*
    分页查询班级信息
     */
    @GetMapping
    public Result Page (ClazzQueryParam clazzQueryParam){
        log.info("分页查询班级信息，参数：{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }
    /*
    新增班级
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("新增班级，参数：{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }
    /*
    根据id查询班级信息(查询回显)
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询班级信息，id:{}",id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }
    /*
    修改班级信息
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息，参数：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }
    /*
    删除班级信息
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("删除班级信息，参数：{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }
    /*
    查询所有班级信息
     */
    @GetMapping("/list")
    public Result findAll(){
        log.info("查询所有班级信息");
        List<Clazz> list = clazzService.findAll();
        return Result.success(list);
    }
}
