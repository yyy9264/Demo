package com.itheima.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
/*
 * 拦截器
 */
@Slf4j
@Component
public class DemoInterceptor implements HandlerInterceptor{
    // 在请求处理之前进行调用（Controller方法调用之前）
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        return true;
    }
    // 请求处理之后进行调用（Controller方法调用之后）
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion...");
    }
    // 视图渲染完成后执行
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("afterConcurrentHandlingStarted...");
    }
}
