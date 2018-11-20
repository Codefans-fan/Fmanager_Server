package main.java.com.fmanager.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import main.java.com.fmanager.models.JsonObjectResponse;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	private Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
	
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public JsonObjectResponse handle401(ShiroException e) {
        return new JsonObjectResponse(401, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public JsonObjectResponse handle401() {
        return new JsonObjectResponse(401, "Unauthorized");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonObjectResponse globalException(HttpServletRequest request, Throwable ex) {
    	ex.printStackTrace();
    	logger.debug(ex.getMessage());
        return new JsonObjectResponse(getStatus(request).value(), ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
