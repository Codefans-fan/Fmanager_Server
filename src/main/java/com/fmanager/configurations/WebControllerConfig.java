package main.java.com.fmanager.configurations;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import main.java.com.fmanager.interceptors.ControllerInterceptor;

@Configuration
public class WebControllerConfig implements WebMvcConfigurer {

    @Resource
    private ControllerInterceptor controllerInterceptor;
    
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(controllerInterceptor);
    }
    
}
