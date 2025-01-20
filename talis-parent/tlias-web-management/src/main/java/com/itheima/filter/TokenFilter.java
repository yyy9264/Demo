package com.itheima.filter;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/*
 * 令牌解析过滤器
 */
@Slf4j
@WebFilter(urlPatterns = "/*")// 拦截所有请求
public class TokenFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //1.获取请求url
        String url = request.getRequestURI();//获取请求路径
        //2.判断url中是否包含/login,如果包含，放行
        if(url.contains("/login") ){
            filterChain.doFilter(request,response);
            return;
        }
        //3.从请求头中获取token
        String token = request.getHeader("token");
        //4.判断token是否存在,如果不存在，响应401
        if(token==null||token.isEmpty()){
            log.info("token为空");
            response.setStatus(401);
            return;
        }
        //5.解析token，如果解析失败，响应401
        try {
            Claims claims = JwtUtils.parseJWT(token);
            //将用户ID存入ThreadLocal中
            Integer id = Integer.valueOf(claims.get("id").toString());
            log.info("用户id为：{}",id);
            CurrentHolder.setCurrentId(id);
        } catch (Exception e) {
            log.info("令牌无效");
            response.setStatus(401);
            return;
        }
        //6.放行
        filterChain.doFilter(request,response);
        //7.从ThreadLocal中移除用户id
        CurrentHolder.remove();
    }
}
