package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    /*
    学生信息分页查询
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询，参数为{}",studentQueryParam);
        return Result.success(studentService.page(studentQueryParam));
    }
    /*
    新增学生信息
     */
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("新增学生信息，学生信息为{}",student);
        studentService.save(student);
        return Result.success();
    }
    /*
    根据id查询学生信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询学生信息，id:{}",id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }
    /*
    修改学生信息
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生信息，学生信息为{}",student);
        studentService.update(student);
        return Result.success();
    }
    /*
    删除学生信息
     */
    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable List<Integer> ids){
        log.info("根据ID删除学生信息，id:{}",ids);
        studentService.deleteById(ids);
        return Result.success();
    }
    /*
    违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,@PathVariable Integer score){
        log.info("违纪处理，学生id:{},违纪分数:{}",id,score);
        studentService.violation(id,score);
        return Result.success();
    }
}
