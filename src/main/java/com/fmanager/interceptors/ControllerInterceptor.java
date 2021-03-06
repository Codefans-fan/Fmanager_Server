package main.java.com.fmanager.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component
public class ControllerInterceptor extends HandlerInterceptorAdapter {
    private Log logger = LogFactory.getLog(ControllerInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("pre Handle method:" + handler.getClass().toString()); //$NON-NLS-1$
        return true;
    }
    
    
    
}
