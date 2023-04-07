package com.event.core.controller;

import com.event.core.controller.api.TicketApi;
import com.event.core.dto.TicketDto;
import com.event.core.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController implements TicketApi {

    private final TicketService ticketService;

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        return ticketService.create(ticketDto);
    }

    @Override
    public TicketDto getTicket(long id) {
        return ticketService.findById(id);
    }

    @Override
    public TicketDto updateTicket(long id, TicketDto ticketDto) {
        return ticketService.update(id, ticketDto);
    }

    @Override
    public List<TicketDto> getTicketList() {
        return ticketService.findAll();
    }

    @Override
    public List<TicketDto> getTicketListByEventId(long eventId) {
        return ticketService.findAllByEventId(eventId);
    }

    @Override
    public void deleteTicket(long id) {
        ticketService.deleteById(id);
    }
}
