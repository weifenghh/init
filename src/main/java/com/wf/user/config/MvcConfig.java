package com.wf.user.config;

import com.wf.user.intercepter.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/30 20:15
 * @Package com.wf.user.config
 * @Version 1.0
 * @Since 1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new UserLoginInterceptor());
        String[] knif4jPath = new String[]{"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
                "/api", "/api-docs", "/api-docs/**", "/doc.html/**"};
        registration
                .addPathPatterns("/**")
                .excludePathPatterns("/user/**")
                .excludePathPatterns(knif4jPath);

    }
}
