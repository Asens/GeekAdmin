package com.geekutil.config;

import com.geekutil.modules.sys.interceptor.CheckLoginInterceptor;
import com.geekutil.modules.sys.interceptor.SysInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Asens
 * create 2019-07-20 22:14
 **/
@ControllerAdvice
@Configuration
public class SysWebConfig  implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sysInterceptor())
                .addPathPatterns("/api/sys/**");

        registry.addInterceptor(checkLoginInterceptor())
                .addPathPatterns("/api/sys/**");
    }

    @Bean
    public SysInterceptor sysInterceptor(){
        return new SysInterceptor();
    }

    @Bean
    public CheckLoginInterceptor checkLoginInterceptor(){
        return new CheckLoginInterceptor();
    }
}
