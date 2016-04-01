package com.epam.springadvanced.service;

import com.epam.springadvanced.domain.entity.Event;
import com.epam.springadvanced.domain.entity.Ticket;
import com.epam.springadvanced.domain.entity.User;
import com.epam.springadvanced.service.exception.EventNotAssignedException;
import com.epam.springadvanced.service.exception.TicketAlreadyBookedException;
import com.epam.springadvanced.service.exception.TicketWithoutEventException;
import com.epam.springadvanced.service.exception.UserNotRegisteredException;

import java.time.LocalDateTime;
import java.util.Collection;

public interface BookingService {

    float getTicketPrice(Event event, LocalDateTime dateTime, Collection<Integer> seatNumbers, User user) throws UserNotRegisteredException, EventNotAssignedException;

    void bookTicket(User user, Ticket ticket) throws UserNotRegisteredException, TicketAlreadyBookedException, TicketWithoutEventException;

    Collection<Ticket> getFreeTickets(long eventId);

    Collection<Ticket> getBookedTickets(long eventId);

}
