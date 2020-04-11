package com.person.springdemo.exception;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest re,Exception e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",e);
        modelAndView.addObject("url",re.getRequestURL());
        modelAndView.setViewName("errorPage");
        return modelAndView;
    }
}

