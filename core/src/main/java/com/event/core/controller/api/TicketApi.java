package com.event.core.controller.api;

import com.event.core.dto.EventDto;
import com.event.core.dto.TicketDto;
import com.event.core.model.Category;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RequestMapping("/ticket")
@RefreshScope
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

    @PutMapping("/buy/{id}")
    @ResponseStatus(HttpStatus.OK)
    TicketDto buyTicket(@PathVariable @Min(1) long id, @RequestParam Category category);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TicketDto> getTicketList();

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    List<TicketDto> getTicketListByEventId(@RequestParam long eventId);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteTicket(@PathVariable @Min(1) long id);

    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.OK)
    List<TicketDto> generateTicketList(@RequestParam @Min(1) int amount, @RequestBody EventDto eventDto);

}
