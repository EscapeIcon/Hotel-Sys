package com.hotel.config;

import com.hotel.interceptor.GlobalInterceptor;
import com.hotel.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Autowired
    private GlobalInterceptor globalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor)
                .addPathPatterns("/**");

        /*registry.addInterceptor(jwtInterceptor)
                // 拦截所有请求
                .addPathPatterns("/**")

                // 排除规则
                .excludePathPatterns(
                        // 登录不拦截
                        "/user/login",
                        // 访问某些公开资源不拦截
                        "/common/**"
                );*/
    }

}
