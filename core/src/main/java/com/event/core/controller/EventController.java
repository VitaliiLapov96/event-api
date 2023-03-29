package com.event.core.controller;

import com.event.core.controller.api.EventApi;
import com.event.core.dto.EventDto;
import com.event.core.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    private final EventService eventService;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        return eventService.create(eventDto);
    }

    @Override
    public EventDto getEvent(long id) {
        return eventService.findById(id);
    }

    @Override
    public List<EventDto> getEventList() {
        return eventService.findAll();
    }

    @Override
    public void deleteEvent(long id) {
        eventService.deleteById(id);
    }

}
