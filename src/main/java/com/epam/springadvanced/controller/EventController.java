package com.epam.springadvanced.controller;

import com.epam.springadvanced.domain.entity.Auditorium;
import com.epam.springadvanced.domain.entity.Event;
import com.epam.springadvanced.domain.enums.Rating;
import com.epam.springadvanced.service.EventService;
import com.epam.springadvanced.service.exception.AuditoriumAlreadyAssignedException;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
                              //@ModelAttribute("rating") Rating rating,
                              //@ModelAttribute("dateTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTime,
                              BindingResult bindingResult) throws AuditoriumAlreadyAssignedException {
        eventService.create(event.getName(), event.getDateTime(), event.getTicketPrice(), event.getRating());
        return "redirect:/events";
    }

    @RequestMapping(value = "/auditorium", params = {"eventId"}, method = RequestMethod.GET)
    public ModelAndView assignEventAuditoriumForm(@RequestParam("eventId") Integer eventId, ModelMap model) {
        model.addAttribute("event", eventService.getById(eventId));
        model.addAttribute("auditorium", auditoriums);
        return new ModelAndView("event-auditorium", model);
    }

    @RequestMapping(value = "/auditorium", method = RequestMethod.POST)
    public String assignEventAuditorium(@ModelAttribute("event") Event event,
                                        @ModelAttribute("auditorium") Auditorium auditorium)
            throws AuditoriumAlreadyAssignedException {
        eventService.assignAuditorium(eventService.getById(event.getId()), auditorium, event.getDateTime());
        return "redirect:/events";
    }

}
