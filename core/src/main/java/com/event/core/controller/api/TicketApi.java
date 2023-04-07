package com.event.core.controller.api;

import com.event.core.dto.TicketDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RequestMapping("/ticket")
public interface TicketApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TicketDto createTicket(@RequestBody @Valid TicketDto ticketDto);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TicketDto getTicket(@PathVariable @Min(1) long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TicketDto updateTicket(@PathVariable @Min(1) long id, @RequestBody TicketDto ticketDto);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TicketDto> getTicketList();

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    List<TicketDto> getTicketListByEventId(@RequestParam long eventId);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteTicket(@PathVariable @Min(1) long id);

}
