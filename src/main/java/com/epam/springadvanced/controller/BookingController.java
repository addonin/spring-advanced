package com.epam.springadvanced.controller;

import com.epam.springadvanced.domain.entity.Event;
import com.epam.springadvanced.domain.entity.Ticket;
import com.epam.springadvanced.service.BookingService;
import com.epam.springadvanced.service.UserService;
import com.epam.springadvanced.service.exception.EventNotAssignedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collections;

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
    private UserService userService;

    @RequestMapping(value = "/free-tickets", params = "eventId", method = RequestMethod.GET)
    public ModelAndView getFreeTickets(@RequestParam("eventId") long eventId, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("/free-tickets");
        model.addAttribute("eventId", eventId);
        model.addAttribute("tickets", bookingService.getFreeTickets(eventId));
        return modelAndView;
    }

    @RequestMapping(value = "/booked-tickets", params = "eventId", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@RequestParam("eventId") long eventId, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("/booked-tickets");
        model.addAttribute("tickets", bookingService.getBookedTickets(eventId));
        return modelAndView;
    }

    @RequestMapping(value = "/tickets", params = {"eventId", "ticketId"}, method = RequestMethod.GET)
    public ModelAndView getTicket(@RequestParam("ticketId") Integer ticketId,
                                  @RequestParam("eventId") Integer eventId,
                                  Principal principal) throws EventNotAssignedException {
        ModelAndView modelAndView = new ModelAndView("/ticket");
        Ticket ticket = bookingService.getTicket(ticketId);
        Event event = ticket.getEvent();
        ticket.setPrice(bookingService.getTicketPrice(
                event, event.getDateTime(),
                    Collections.singletonList(ticket.getSeat().getNumber()),
                        userService.getUserByEmail(principal.getName())));
        return modelAndView;
    }

}


