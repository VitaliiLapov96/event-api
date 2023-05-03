package com.event.core.controller;

import com.event.core.controller.api.EventApi;
import com.event.core.dto.EventDto;
import com.event.core.service.EventService;
import com.netflix.servo.annotations.Monitor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    private final EventService eventService;

    @Monitor(name = "createEventCounter")
    @Override
    public EventDto createEvent(EventDto eventDto) {
        return eventService.create(eventDto);
    }

    @Monitor(name = "getEventCounter")
    @Override
    public EventDto getEvent(long id) {
        return eventService.findById(id);
    }

    @Monitor(name = "updateEventCounter")
    @Override
    public EventDto updateEvent(long id, EventDto eventDto) {
        EventDto eventToUpdate = eventService.findById(id);

        eventToUpdate.setName(eventDto.getName());
        eventToUpdate.setDescription(eventDto.getDescription());
        eventToUpdate.setPlace(eventDto.getPlace());
        eventToUpdate.setPrice(eventDto.getPrice());

        return eventService.update(id, eventToUpdate);
    }

    @Monitor(name = "getEventListCounter")
    @Override
    public List<EventDto> getEventList() {
        return eventService.findAll();
    }

    @Monitor(name = "deleteEventCounter")
    @Override
    public void deleteEvent(long id) {
        eventService.deleteById(id);
    }

}
