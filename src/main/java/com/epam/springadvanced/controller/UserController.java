package com.epam.springadvanced.controller;

import com.epam.springadvanced.domain.entity.Ticket;
import com.epam.springadvanced.service.UserService;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUsersPage(ModelMap model) {
        model.put("users", userService.getAll());
        return new ModelAndView("users", model);
    }

    @RequestMapping(value = "/tickets", params = "userId", method = RequestMethod.GET)
    public String getBookedTickets(@RequestParam("userId") int userId) {

        Collection<Ticket> tickets = userService.getBookedTicketsByUserId(userId);
        /*model.addAttribute("tickets", tickets);

        List<TicketReportDto> ticketBeans = tickets.stream()
                .map(t -> new TicketReportDto(t))
                .collect(Collectors.toList());

        model.addAttribute("datasource", new JRBeanCollectionDataSource(ticketBeans));
        model.addAttribute("format", "pdf");
        return "rpt_tickets";*/
        return null;
    }

}
