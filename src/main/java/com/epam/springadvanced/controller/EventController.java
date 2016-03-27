package com.epam.springadvanced.controller;

import com.epam.springadvanced.entity.Event;
import com.epam.springadvanced.service.BookingService;
import com.epam.springadvanced.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * @author dmytro_adonin
 * @since 3/22/2016.
 */
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getEventsPage() {
        Collection<Event> events = eventService.getAll();
        ModelAndView modelAndView = new ModelAndView("events");
        modelAndView.addObject("events", events);
        return modelAndView;
    }

    @RequestMapping(value = "/tickets", params = "eventId", method = RequestMethod.GET)
    public ModelAndView getTickets(@RequestParam("eventId") int eventId, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("/tickets");
        model.addAttribute("tickets", bookingService.getTicketsForEvent(eventId));
        return modelAndView;
    }

}
