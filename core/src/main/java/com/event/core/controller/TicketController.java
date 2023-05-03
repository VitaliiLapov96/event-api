package com.event.core.controller;

import com.event.core.controller.api.TicketApi;
import com.event.core.dto.TicketDto;
import com.event.core.service.TicketService;
import com.netflix.servo.annotations.Monitor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController implements TicketApi {

    private final TicketService ticketService;

    @Monitor(name = "createTicketCounter")
    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        return ticketService.create(ticketDto);
    }

    @Monitor(name = "getTicketCounter")
    @Override
    public TicketDto getTicket(long id) {
        return ticketService.findById(id);
    }

    @Monitor(name = "updateTicketCounter")
    @Override
    public TicketDto updateTicket(long id, TicketDto ticketDto) {
        return ticketService.update(id, ticketDto);
    }

    @Monitor(name = "getTicketListCounter")
    @Override
    public List<TicketDto> getTicketList() {
        return ticketService.findAll();
    }

    @Monitor(name = "getTicketListByEventIdCounter")
    @Override
    public List<TicketDto> getTicketListByEventId(long eventId) {
        return ticketService.findAllByEventId(eventId);
    }

    @Monitor(name = "deteleTicketCounter")
    @Override
    public void deleteTicket(long id) {
        ticketService.deleteById(id);
    }
}
