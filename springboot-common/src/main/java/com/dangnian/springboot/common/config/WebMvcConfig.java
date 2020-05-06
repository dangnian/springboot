package com.dangnian.springboot.common.config;

import com.dangnian.springboot.common.response.interceptor.ResponseResultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author chun.yin
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String allPathPatterns = "/**";
        String[] excludePathPatterns = new String[] {"/img/**","/js/**","/css/**","/fonts/**"};
        registry.addInterceptor(new ResponseResultInterceptor()).addPathPatterns(allPathPatterns).excludePathPatterns(excludePathPatterns);
    }

}
