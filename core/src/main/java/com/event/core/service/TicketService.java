package com.event.core.service;

import com.event.core.dto.TicketDto;

import java.util.List;

public interface TicketService {

    TicketDto create(TicketDto ticketDto);

    TicketDto findById(long id);

    TicketDto update(long id, TicketDto ticketDto);

    List<TicketDto> findAll();

    List<TicketDto> findAllByEventId(long eventId);

    List<TicketDto> saveAll(List<TicketDto> ticketsDto);

    void deleteById(long id);
}
