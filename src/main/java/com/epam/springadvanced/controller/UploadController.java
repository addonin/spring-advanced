package com.epam.springadvanced.controller;

import com.epam.springadvanced.domain.entity.Event;
import com.epam.springadvanced.domain.entity.User;
import com.epam.springadvanced.service.EventService;
import com.epam.springadvanced.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author dmytro_adonin
 * @since 3/23/2016.
 */
@Controller
@RequestMapping("/uploads")
public class UploadController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUploadsPage() {
        return new ModelAndView("uploads");
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String uploadUsersFileAndShowUsersPage(@RequestParam("users") MultipartFile multipartFile, ModelMap model)
            throws IOException {
        List<User> users = Arrays.asList(objectMapper.readValue(multipartFile.getBytes(), User[].class));
        users.stream().forEach(userService::register);
        model.put("users", userService.getAll());
        return "redirect:/users";
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String uploadEventsFileAndShowEventsPage(@RequestParam("events") MultipartFile multipartFile, ModelMap model)
            throws IOException {
        List<Event> events = Arrays.asList(objectMapper.readValue(multipartFile.getBytes(), Event[].class));
        events.stream().forEach(event -> {
            eventService.create(event.getName(), event.getDateTime(), event.getTicketPrice(), event.getRating());
        });
        model.put("events", eventService.getAll());
        return "redirect:/events";
    }

}
