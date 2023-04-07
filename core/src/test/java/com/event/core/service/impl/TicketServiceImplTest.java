package com.event.core.service.impl;

import com.event.core.dto.TicketDto;
import com.event.core.exception.EntityNotFoundException;
import com.event.core.model.Ticket;
import com.event.core.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.event.core.util.EventConstantsTest.MOCK_ID;
import static com.event.core.util.TestUtil.buildDefaultTicket;
import static com.event.core.util.TestUtil.buildDefaultTicketDto;
import static com.event.core.util.TestUtil.buildDefaultTicketWithCustomId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketRepository ticketRepository;

    private final Ticket expectedTicket = buildDefaultTicket();

    @Test
    void shouldCreateTicket() {
        // given
        when(ticketRepository.save(any())).thenReturn(expectedTicket);

        // when
        TicketDto actualTicketDto = ticketService.create(new TicketDto());

        // then
        assertEquals(expectedTicket.getId(), actualTicketDto.getId());
        assertEquals(expectedTicket.getCategory(), actualTicketDto.getCategory());
        assertEquals(expectedTicket.getCreatedDate(), actualTicketDto.getCreatedDate());
    }

    @Test
    void shouldFindTicketById() {
        // given
        when(ticketRepository.findById(any())).thenReturn(Optional.of(expectedTicket));

        // when
        TicketDto actualTicketDto = ticketService.findById(MOCK_ID);

        // then
        assertEquals(expectedTicket.getId(), actualTicketDto.getId());
        assertEquals(expectedTicket.getCategory(), actualTicketDto.getCategory());
        assertEquals(expectedTicket.getCreatedDate(), actualTicketDto.getCreatedDate());
    }

    @Test
    void shouldThrowExceptionWhenTicketIsNotFound() {
        //given
        String expectedExceptionMsg = "FAILED to find Ticket with id: 1";

        when(ticketRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //when
        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () ->
                ticketService.findById(MOCK_ID));

        //then
        assertEquals(expectedExceptionMsg, entityNotFoundException.getMessage());
    }

    @Test
    void shouldUpdateTicket() {
        // given
        TicketDto expectedTicketDto = buildDefaultTicketDto();

        when(ticketRepository.findById(any())).thenReturn(Optional.of(expectedTicket));
        when(ticketRepository.save(any())).thenReturn(expectedTicket);

        // when
        TicketDto actualTicketDto = ticketService.update(expectedTicketDto.getId(), expectedTicketDto);

        // then
        assertEquals(expectedTicket.getId(), actualTicketDto.getId());
        assertEquals(expectedTicket.getCategory(), actualTicketDto.getCategory());
        assertEquals(expectedTicket.getCreatedDate(), actualTicketDto.getCreatedDate());
    }

    @Test
    void shouldFindAllTickets() {
        // given
        List<Ticket> expectedTicketList = List.of(buildDefaultTicketWithCustomId(1L),
                buildDefaultTicketWithCustomId(2L),
                buildDefaultTicketWithCustomId(3L));
        when(ticketRepository.findAll()).thenReturn(expectedTicketList);

        // when
        List<TicketDto> actualTicketDtoList = ticketService.findAll();

        // then
        List<Long> expectedTicketIdList = expectedTicketList.stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        List<Long> actualTicketDtoIdList = actualTicketDtoList.stream()
                .map(TicketDto::getId)
                .collect(Collectors.toList());

        assertEquals(expectedTicketList.size(), actualTicketDtoList.size());
        assertEquals(expectedTicketIdList, actualTicketDtoIdList);
    }

    @Test
    void shouldFindAllTicketsByEventId() {
        // given
        List<Ticket> expectedTicketList = List.of(buildDefaultTicketWithCustomId(1L),
                buildDefaultTicketWithCustomId(2L),
                buildDefaultTicketWithCustomId(3L));
        when(ticketRepository.findAllByEventId(MOCK_ID)).thenReturn(expectedTicketList);

        // when
        List<TicketDto> actualTicketDtoList = ticketService.findAllByEventId(MOCK_ID);

        // then
        List<Long> expectedTicketIdList = expectedTicketList.stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        List<Long> actualTicketDtoIdList = actualTicketDtoList.stream()
                .map(TicketDto::getId)
                .collect(Collectors.toList());

        assertEquals(expectedTicketList.size(), actualTicketDtoList.size());
        assertEquals(expectedTicketIdList, actualTicketDtoIdList);
    }

    @Test
    void shouldDeleteTicketById() {
        // given

        // when
        ticketService.deleteById(MOCK_ID);

        // then
        verify(ticketRepository).deleteById(any());
    }

}