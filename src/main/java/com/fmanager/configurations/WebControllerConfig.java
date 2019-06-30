package main.java.com.fmanager.configurations;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import main.java.com.fmanager.interceptors.ControllerInterceptor;
import main.java.com.fmanager.utils.ConstantString;

@Configuration
public class WebControllerConfig implements WebMvcConfigurer {

    @Resource
    private ControllerInterceptor controllerInterceptor;
    
    @Value("${fmanager.file.resources}")
	private String fileRootPath;
    
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(controllerInterceptor);
    }


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/icons/**").addResourceLocations("file:"+ fileRootPath+File.separator+ConstantString.ICONS_FOLDER_NAME+File.separator);
	}
    
    
    
}
