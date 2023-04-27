package com.event.core.mapper;

import com.event.core.dto.EventDto;
import com.event.core.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event eventDtoMapToEvent(EventDto eventDto);

    EventDto eventMapToEventDto(Event event);

    List<Event> eventDtoListMapToEventList(List<EventDto> eventDtoList);

    List<EventDto> eventListMapToEventDtoList(List<Event> eventList);

}
