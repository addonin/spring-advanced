package com.epam.springadvanced.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author dmytro_adonin
 * @since 3/22/2016.
 */
@Controller
@RequestMapping("/")
public class BookingController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView testMapping(ModelMap model) {
        return new ModelAndView("index", model);
    }

}
