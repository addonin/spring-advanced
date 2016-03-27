package com.epam.springadvanced.repository;

import com.epam.springadvanced.domain.entity.Ticket;
import com.epam.springadvanced.domain.entity.User;

import java.util.Collection;

public interface TicketRepository {

    Ticket save(Ticket ticket);

    Collection<Ticket> getAll();

    Collection<Ticket> getByEventId(int eventId);

    Collection<Ticket> getBookedTickets();

    Collection<Ticket> getBookedTicketsByUserId(long userId);

    void saveBookedTicket(User user, Ticket ticket);

    void deleteBookedTicketByUserId(long userId);

}
