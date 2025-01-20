package com.itheima.aspect;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class OperationAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;


    // 环绕通知：记录方法执行的详细信息
    @Around("@annotation(com.itheima.anno.LogOperation)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 获取操作人ID（假设从上下文中获取）
        Integer operateEmpId = getOperateEmpId();

        // 获取目标类的全类名
        String className = joinPoint.getTarget().getClass().getName();

        // 获取目标方法的方法名
        String methodName = joinPoint.getSignature().getName();

        // 获取方法运行时参数
        String methodParams = Arrays.toString(joinPoint.getArgs());

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 记录结束时间
        long endTime = System.currentTimeMillis();

        // 计算方法执行时长
        long costTime = endTime - startTime;

        // 获取返回值
        String returnValue = result == null ? "" : result.toString();

        // 创建操作日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateEmpId);
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(methodParams);
        operateLog.setReturnValue(returnValue);
        operateLog.setCostTime(costTime);

        // 保存操作日志
        operateLogMapper.insert(operateLog);

        // 返回结果
        return result;
    }

    // 假设从上下文中获取操作人ID的方法
    private Integer getOperateEmpId() {
        // 这里可以根据实际情况从上下文中获取操作人ID，例如从Session中获取
        return CurrentHolder.getCurrentId();
    }
}

