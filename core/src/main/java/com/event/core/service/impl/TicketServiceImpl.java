package com.event.core.service.impl;

import com.event.core.dto.TicketDto;
import com.event.core.exception.EntityNotFoundException;
import com.event.core.mapper.EventMapper;
import com.event.core.mapper.TicketMapper;
import com.event.core.model.Event;
import com.event.core.model.Ticket;
import com.event.core.repository.TicketRepository;
import com.event.core.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public TicketDto create(TicketDto ticketDto) {
        Ticket ticket = TicketMapper.INSTANCE.ticketDtoMapToTicket(ticketDto);
        ticket.setCreatedDate(LocalDateTime.now());

        Ticket savedTicket = ticketRepository.save(ticket);
        log.info("Insert TICKET with id: [{}]", savedTicket.getId());
        log.debug("Insert TICKET: [{}]", savedTicket);

        return TicketMapper.INSTANCE.ticketMapToTicketDto(savedTicket);
    }

    @Override
    public TicketDto findById(long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Ticket.class, id));

        log.info("Get TICKET with id: [{}]", ticket.getId());
        log.debug("Get TICKET: [{}]", ticket);

        return TicketMapper.INSTANCE.ticketMapToTicketDto(ticket);
    }

    @Override
    public TicketDto update(long id, TicketDto ticketDto) {
        Ticket ticketToUpdate = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Ticket.class, id));

        Event event = EventMapper.INSTANCE.eventDtoMapToEvent(ticketDto.getEvent());
        ticketToUpdate.setEvent(event);
        ticketToUpdate.setCategory(ticketDto.getCategory());

        Ticket updatedTicket = ticketRepository.save(ticketToUpdate);
        log.info("Update TICKET with id: [{}]", updatedTicket.getId());
        log.debug("Update TICKET: [{}]", updatedTicket);

        return TicketMapper.INSTANCE.ticketMapToTicketDto(updatedTicket);
    }

    @Override
    public List<TicketDto> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        log.info("Fetch TICKET list: size [{}]", tickets.size());
        log.debug("Fetch TICKET list: {}", tickets);

        return TicketMapper.INSTANCE.ticketListMapToTicketDtoList(tickets);
    }

    @Override
    public List<TicketDto> findAllByEventId(long eventId) {
        List<Ticket> tickets = ticketRepository.findAllByEventId(eventId);
        log.info("Fetch TICKET list: size [{}]", tickets.size());
        log.debug("Fetch TICKET list: {}", tickets);

        return TicketMapper.INSTANCE.ticketListMapToTicketDtoList(tickets);
    }

    @Override
    public void deleteById(long id) {
        ticketRepository.deleteById(id);
        log.info("Delete TICKET by id: [{}]", id);
    }

}
