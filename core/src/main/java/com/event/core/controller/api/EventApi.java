package com.event.core.controller.api;

import com.event.core.dto.EventDto;
import com.event.core.model.Event;
import com.event.core.model.Role;
import com.event.core.model.Ticket;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequestMapping("/event")
@RefreshScope
public interface EventApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    EventDto createEvent(@RequestBody @Valid EventDto eventDto);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    EventDto getEvent(@PathVariable @Min(1) long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    EventDto updateEvent(@PathVariable @Min(1) long id, @RequestBody EventDto eventDto);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<EventDto> getEventList();

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteEvent(@PathVariable @Min(1) long id);

    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.OK)
    List<EventDto> generateEventList(@RequestParam @Min(1) int amount);

}
