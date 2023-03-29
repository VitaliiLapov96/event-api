package com.event.core.controller.api;

import com.event.core.dto.EventDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RequestMapping("/event")
public interface EventApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EventDto createEvent(@RequestBody @Valid EventDto eventDto);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    EventDto getEvent(@PathVariable @Min(1) long id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<EventDto> getEventList();

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteEvent(@PathVariable @Min(1) long id);

}
