package com.epam.springadvanced.controller;

import com.epam.springadvanced.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping({"/booking"})
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/tickets", params = "eventId", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(int eventId, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("/booked-tickets");
        model.addAttribute("tickets", bookingService.getTicketsForEvent(eventId));
        return modelAndView;
    }

}


