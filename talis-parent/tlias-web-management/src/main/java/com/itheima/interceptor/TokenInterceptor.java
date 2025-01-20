package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1.获取请求url
//        String url = request.getRequestURI();//获取请求路径
//        //2.判断url中是否包含/login,如果包含，放行
//        if(url.contains("/login") ){
//            return true;
//        }
        //3.从请求头中获取token
        String token = request.getHeader("token");
        //4.判断token是否存在,如果不存在，响应401
        if(token==null||token.isEmpty()){
            log.info("token为空");
            response.setStatus(401);
            return false;
        }
        //5.解析token，如果解析失败，响应401
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌无效");
            response.setStatus(401);
            return false;
        }
        //6.放行
        log.info("放行");
        return true;
    }
}
