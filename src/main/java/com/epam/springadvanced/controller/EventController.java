package com.epam.springadvanced.controller;

import com.epam.springadvanced.domain.entity.Auditorium;
import com.epam.springadvanced.domain.entity.Event;
import com.epam.springadvanced.domain.enums.Rating;
import com.epam.springadvanced.service.EventService;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author dmytro_adonin
 * @since 3/22/2016.
 */
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private List<Auditorium> auditoriums;

    @Autowired
    private EventService eventService;

    @Autowired
    private PropertyEditorRegistrar customPropertyEditorRegistrar;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        customPropertyEditorRegistrar.registerCustomEditors(binder);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getEventsPage() {
        Collection<Event> events = eventService.getAll();
        ModelAndView modelAndView = new ModelAndView("events");
        modelAndView.addObject("events", events);
        return modelAndView;
    }

    @RequestMapping(value= "/create-form", method=RequestMethod.GET)
    public ModelAndView createEventForm(ModelMap model) {
        model.addAttribute(new Event());
        model.addAttribute("rating", Rating.values());
        model.addAttribute("auditorium", auditoriums);
        return new ModelAndView("event-form", model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createEvent(@ModelAttribute("event") Event event,
                              @ModelAttribute("rating") Rating rating,
                              @ModelAttribute("auditorium") Auditorium auditorium,
                              @ModelAttribute("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.NONE) LocalDateTime dateTime,
                              BindingResult bindingResult, ModelMap model) {
        eventService.create(event.getName(), dateTime, event.getTicketPrice(), rating);
        return "events";
    }

}
