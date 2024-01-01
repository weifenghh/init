package com.wf.user.aop;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wf.user.annotation.AuthCheck;
import com.wf.user.exception.BusinessException;
import com.wf.user.model.entity.User;
import com.wf.user.service.UserService;
import com.wf.user.utils.jwt.JwtUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/31 9:53
 * @Package com.wf.user.aop
 * @Version 1.0
 * @Since 1.0
 */

@Component
@Aspect()
public class AuthCheckInterceptor {

    @Resource
    private UserService userService;

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        long userAccountId = JwtUtil.getUserAccountId(request);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userAccountId);
        User user = userService.getOne(queryWrapper);

        if(user == null){
            throw new BusinessException("获取用户失败(检验是否未管理员)",40000);
        }
        if(!(mustRole.equals(user.getUserRole()))) {
            String userRole = user.getUserRole();
            throw new BusinessException("非管理员用户无法操作(检验是否未管理员)",40000);
        }
        return joinPoint.proceed();
    }

}
