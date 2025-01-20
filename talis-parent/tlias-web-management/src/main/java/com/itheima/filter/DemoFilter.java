package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")// 拦截所有请求
public class DemoFilter implements Filter {
    //初始化方法,web服务器启动时执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init...");
    }
    //拦截到请求时执行,可执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求...");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
    //销毁方法,web服务器停止时执行一次
    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}
