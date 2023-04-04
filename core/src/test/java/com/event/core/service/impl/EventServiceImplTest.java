package com.event.core.service.impl;

import com.event.core.dto.EventDto;
import com.event.core.exception.EntityNotFoundException;
import com.event.core.model.Event;
import com.event.core.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.event.core.util.EventConstantsTest.MOCK_ID;
import static com.event.core.util.TestUtil.buildDefaultEvent;
import static com.event.core.util.TestUtil.buildDefaultEventDto;
import static com.event.core.util.TestUtil.buildDefaultEventWithCustomId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    private static final Long ID = 1L;

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventRepository eventRepository;

    private final EventDto expectedEventDto = buildDefaultEventDto();
    private final Event expectedEvent = buildDefaultEvent();

    @Test
    void shouldCreateEvent() {
        // given
        when(eventRepository.save(any())).thenReturn(expectedEvent);

        // when
        EventDto actualEventDto = eventService.create(new EventDto());

        // then
        assertEquals(expectedEvent.getId(), actualEventDto.getId());
        assertEquals(expectedEvent.getName(), actualEventDto.getName());
        assertEquals(expectedEvent.getDescription(), actualEventDto.getDescription());
        assertEquals(expectedEvent.getPlace(), actualEventDto.getPlace());
        assertEquals(expectedEvent.getPrice(), actualEventDto.getPrice());
    }

    @Test
    void shouldFindEventById() {
        // given
        when(eventRepository.findById(any())).thenReturn(Optional.of(expectedEvent));

        // when
        EventDto actualEventDto = eventService.findById(ID);

        // then
        assertEquals(expectedEvent.getId(), actualEventDto.getId());
        assertEquals(expectedEvent.getName(), actualEventDto.getName());
        assertEquals(expectedEvent.getDescription(), actualEventDto.getDescription());
        assertEquals(expectedEvent.getPlace(), actualEventDto.getPlace());
        assertEquals(expectedEvent.getPrice(), actualEventDto.getPrice());
    }

    @Test
    void shouldThrowExceptionWhenEventIsNotFound() {
        //given
        String expectedExceptionMsg = "FAILED to find Event: 1";

        when(eventRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //when
        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () ->
                eventService.findById(MOCK_ID));

        //then
        assertEquals(expectedExceptionMsg, entityNotFoundException.getMessage());
    }

    @Test
    void shouldUpdateEvent() {
        // given
        when(eventRepository.findById(any())).thenReturn(Optional.of(expectedEvent));
        when(eventRepository.save(any())).thenReturn(expectedEvent);

        // when
        EventDto actualEventDto = eventService.update(expectedEventDto.getId(), expectedEventDto);

        // then
        assertEquals(expectedEvent.getId(), actualEventDto.getId());
        assertEquals(expectedEvent.getName(), actualEventDto.getName());
        assertEquals(expectedEvent.getDescription(), actualEventDto.getDescription());
        assertEquals(expectedEvent.getPlace(), actualEventDto.getPlace());
        assertEquals(expectedEvent.getPrice(), actualEventDto.getPrice());
    }

    @Test
    void findAllEvents() {
        // given
        List<Event> expectedEventList = List.of(buildDefaultEventWithCustomId(1L),
                buildDefaultEventWithCustomId(2L),
                buildDefaultEventWithCustomId(3L));
        when(eventRepository.findAll()).thenReturn(expectedEventList);

        // when
        List<EventDto> actualEventDtoList = eventService.findAll();

        // then
        List<Long> expectedEventIdList = expectedEventList.stream()
                .map(Event::getId)
                .collect(Collectors.toList());
        List<Long> actualEventDtoIdList = actualEventDtoList.stream()
                .map(EventDto::getId)
                .collect(Collectors.toList());

        assertEquals(expectedEventList.size(), actualEventDtoList.size());
        assertEquals(expectedEventIdList, actualEventDtoIdList);
    }

    @Test
    void shouldDeleteEventById() {
        // given

        // when
        eventService.deleteById(ID);

        // then
        verify(eventRepository).deleteById(any());
    }

}