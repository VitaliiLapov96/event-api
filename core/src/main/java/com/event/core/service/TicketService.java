package com.event.core.service;

import com.event.core.dto.TicketDto;
import com.event.core.model.Category;

import java.util.List;

public interface TicketService {

    TicketDto create(TicketDto ticketDto);

    TicketDto findById(long id);

    TicketDto update(long id, TicketDto ticketDto);

    TicketDto buy(long id, Category ticketDto);

    List<TicketDto> findAll();

    List<TicketDto> findAllByEventId(long eventId);

    List<TicketDto> saveAll(List<TicketDto> ticketsDto);

    void deleteById(long id);
}
