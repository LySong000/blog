package com.lys.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()) //new出拦截操作的类
                .addPathPatterns("/admin/*") //设置拦截路径
                .addPathPatterns("/admin/*/*")
                .addPathPatterns("/admin/*/*/*")
                .addPathPatterns("/admin/*/*/*/*")
                .excludePathPatterns("/admin") //跳过登录路径
                .excludePathPatterns("/admin/login");
    }
}
