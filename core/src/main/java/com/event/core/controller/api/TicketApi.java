package com.event.core.controller.api;

import com.event.core.dto.TicketDto;
import com.event.core.model.Category;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TicketDto> getTicketList();

//    @GetMapping'
//    @ResponseStatus(HttpStatus.OK)
//    List<TicketD'to> getTicketListByCategory(@RequestParam Category category);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteTicket(@PathVariable @Min(1) long id);

}
