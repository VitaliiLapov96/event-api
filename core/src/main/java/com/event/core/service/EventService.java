package com.event.core.service;

import com.event.core.dto.EventDto;

import java.util.List;

public interface EventService {

    EventDto create(EventDto eventDto);

    EventDto findById(long id);

    EventDto update(long id, EventDto eventDto);

    List<EventDto> findAll();

    void deleteById(long id);

}
