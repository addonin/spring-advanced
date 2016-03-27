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
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndexPage(ModelMap model) {
        return new ModelAndView("index", model);
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }

    /*@RequestMapping("/logout")
    public String logout() {
        return "index";
    }*/

}
