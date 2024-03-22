package com.eazy.school.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {
    /**
     * @ExceptionHandler will register the give method for a given
     * exception type, so that ControllerAdvice can invoke this mothod
     * logic if a given exception type is thrown inside the web application
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception exception) {
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        errorPage.addObject("errromsg", exception.getMessage());
        return errorPage;
    }
}
