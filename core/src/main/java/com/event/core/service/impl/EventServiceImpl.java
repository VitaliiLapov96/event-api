package com.event.core.service.impl;

import com.event.core.dto.EventDto;
import com.event.core.exception.EntityNotFoundException;
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
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Event.class, id));
        log.info("Get EVENT with id: [{}]", event.getId());
        log.debug("Get EVENT: [{}]", event);

        return EventMapper.INSTANCE.eventMapToEventDto(event);
    }

    @Override
    public EventDto update(long id, EventDto eventDto) {
        Event eventToUpdate = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Event.class, id));

        eventToUpdate.setName(eventDto.getName());
        eventToUpdate.setDescription(eventDto.getDescription());
        eventToUpdate.setPlace(eventDto.getPlace());
        eventToUpdate.setPrice(eventDto.getPrice());

        Event updatedEvent = eventRepository.save(eventToUpdate);
        log.info("Update EVENT with id: [{}]", updatedEvent.getId());
        log.debug("Update EVENT: [{}]", updatedEvent);

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
    public List<EventDto> saveAll(List<EventDto> eventsDto) {
        List<Event> events = EventMapper.INSTANCE.eventDtoListMapToEventList(eventsDto);

        List<Event> savedEvents = eventRepository.saveAll(events);
        log.info("Save EVENT list: size [{}]", savedEvents.size());
        log.debug("Save EVENT list: {}", savedEvents);

        return EventMapper.INSTANCE.eventListMapToEventDtoList(savedEvents);
    }

    @Override
    public void deleteById(long id) {
        eventRepository.deleteById(id);
        log.info("Delete EVENT by id: [{}]", id);
    }

}
