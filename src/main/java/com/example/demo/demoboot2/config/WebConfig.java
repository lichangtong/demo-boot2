package com.example.demo.demoboot2.config;

import com.example.demo.demoboot2.Interceptor.ApiInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-13 10:55
 * @Description:
 */
@SpringBootConfiguration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new ApiInterceptor()).addPathPatterns("/*");
        super.addInterceptors(registry);
    }
}
