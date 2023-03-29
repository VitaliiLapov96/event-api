package com.event.core.service.impl;

import com.event.core.dto.EventDto;
import com.event.core.mapper.EventMapper;
import com.event.core.model.Event;
import com.event.core.repository.EventRepository;
import com.event.core.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public EventDto create(EventDto eventDto) {
        Event event = EventMapper.INSTANCE.eventDtoMapToEvent(eventDto);

        Event savedEvent = eventRepository.save(event);
        log.info("Insert EVENT with id: [{}]", savedEvent.getId());
        log.debug("Insert EVENT: [{}]", savedEvent);

        return EventMapper.INSTANCE.eventMapToEventDto(savedEvent);
    }

    @Override
    public EventDto findById(long id) {
        Event event = eventRepository.findById(id).orElseThrow();
        log.info("Get EVENT with id: [{}]", event);
        log.debug("Get EVENT: [{}]", event.getId());

        return EventMapper.INSTANCE.eventMapToEventDto(event);
    }

    @Override
    public EventDto update(EventDto eventDto) {
        Event event = EventMapper.INSTANCE.eventDtoMapToEvent(eventDto);

        Event updatedEvent = eventRepository.save(event);
        log.info("Update EVENT with id: [{}]", updatedEvent);
        log.debug("Update EVENT: [{}]", updatedEvent.getId());

        return EventMapper.INSTANCE.eventMapToEventDto(updatedEvent);
    }

    @Override
    public List<EventDto> findAll() {
        List<Event> events = eventRepository.findAll();
        log.info("Fetch EVENT list: size [{}]", events.size());
        log.debug("Fetch EVENT list: {}", events);

        return EventMapper.INSTANCE.eventListMapToEventDtoList(events);
    }

    @Override
    public void deleteById(long id) {
        eventRepository.deleteById(id);
        log.info("Delete EVENT by id: [{}]", id);
    }

}
