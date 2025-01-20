package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //SQLIntegrityConstraintViolationException,DataIntegrityViolationException
    @ExceptionHandler
    public Result handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("服务器发生异常：", e);
        return Result.error("所属班级不能为空,请选择班级");
    }
    //处理数据库主键重复异常
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("服务器发生异常：", e);
        String msg = e.getMessage();
        String substring = msg.substring(msg.indexOf("Duplicate entry"));
        String[] result = substring.split(" ");
        return Result.error("该信息"+result[2]+"已存在,请重新输入");
    }
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("服务器发生异常：", e);
        return Result.error("服务器发生异常,请联系管理员");
    }
    @ExceptionHandler
    public Result deleteException(DeleteException e) {
        log.error("服务器发生异常：", e);
        return Result.error(e.getMessage());
    }
}
