package com.wf.user.intercepter;

import com.wf.user.exception.BusinessException;
import com.wf.user.utils.jwt.JwtUtil;
import org.aopalliance.intercept.Interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/30 20:19
 * @Package com.wf.user.intercepter
 * @Version 1.0
 * @Since 1.0
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = JwtUtil.checkToken(request);
        if(!flag){
            throw new BusinessException("Token异常",40000);
        }
        return true;
    }
}
