package com.ningbai.blog.advice;

import com.ningbai.blog.exception.MyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handler(Throwable ex,
                         Model model){

        if (ex instanceof MyException){
            model.addAttribute("message",ex.getMessage());
        }
        return new ModelAndView("error");
    }

}
