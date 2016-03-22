package com.epam.springadvanced.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author dmytro_adonin
 * @since 3/22/2016.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView viewException(Exception exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
