package com.epam.springadvanced.controller;

import com.epam.springadvanced.service.BookingService;
import com.epam.springadvanced.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author dmytro_adonin
 * @since 3/22/2016.
 */
@Controller
@RequestMapping({"/booking"})
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/free-tickets", params = "eventId", method = RequestMethod.GET)
    public ModelAndView getFreeTickets(@RequestParam("eventId") long eventId, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("/free-tickets");
        model.addAttribute("tickets", bookingService.getFreeTickets(eventId));
        return modelAndView;
    }

    @RequestMapping(value = "/booked-tickets", params = "eventId", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@RequestParam("eventId") long eventId, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("/booked-tickets");
        model.addAttribute("tickets", bookingService.getBookedTickets(eventId));
        return modelAndView;
    }

    @RequestMapping(value = "/fill-account", params = {"userId", "amount"}, method = RequestMethod.POST)
    public String fillAccount(@RequestParam("userId") long userId,
                              @RequestParam("amount") float amount) {
        userAccountService.fillAccount(userId, amount);
        return "redirect:/users";
    }

}


