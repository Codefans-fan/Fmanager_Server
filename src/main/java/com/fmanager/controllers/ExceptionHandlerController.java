package main.java.com.fmanager.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import main.java.com.fmanager.models.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ErrorResponse handle401(ShiroException e) {
        return new ErrorResponse(401, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handle401() {
        return new ErrorResponse(401, "Unauthorized");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse globalException(HttpServletRequest request, Throwable ex) {
        return new ErrorResponse(getStatus(request).value(), ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
