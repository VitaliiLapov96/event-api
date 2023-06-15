package com.event.core.controller;

import com.event.core.dto.TicketDto;
import com.event.core.model.Event;
import com.event.core.model.Ticket;
import com.event.core.repository.EventRepository;
import com.event.core.repository.TicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.event.core.util.TestUtil.buildDefaultEvent;
import static com.event.core.util.TestUtil.buildDefaultTicketDtoWithCustomEventId;
import static com.event.core.util.TestUtil.buildDefaultTicketWithCustomEventId;
import static com.event.core.util.TestUtil.buildDefaultToUpdateTicketDtoWithCustomEventId;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        ticketRepository.deleteAll();
        eventRepository.deleteAll();
    }

    @Test
    void createTicket() throws Exception {
        // given
        Event savedEvent = eventRepository.save(buildDefaultEvent());
        TicketDto expectedTicketDto = buildDefaultTicketDtoWithCustomEventId(savedEvent.getId());

        // when
        ResultActions response = mockMvc.perform(post("/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedTicketDto)));

        // then
        response.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.category", is(expectedTicketDto.getCategory().toString())));
    }

    @Test
    void getTicket() throws Exception {
        // given
        Event savedEvent = eventRepository.save(buildDefaultEvent());
        Ticket savedTicket = ticketRepository.save(buildDefaultTicketWithCustomEventId(savedEvent.getId()));

        // when
        ResultActions response = mockMvc.perform(get("/ticket/{id}", savedTicket.getId()));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.category", is(savedTicket.getCategory().toString())));
    }

    @Test
    void getInvalidTicket() throws Exception {
        // given

        // when
        ResultActions response = mockMvc.perform(get("/ticket/{id}", 1));

        // then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

//    @Test
//    void updateTicket() throws Exception {
//        // given
//        Event savedEvent = eventRepository.save(buildDefaultEvent());
//        Ticket savedTicket = ticketRepository.save(buildDefaultTicketWithCustomEventId(savedEvent.getId()));
//
//        TicketDto ticketDtoToUpdate = buildDefaultToUpdateTicketDtoWithCustomEventId(savedEvent.getId());
//
//        // when
//        ResultActions response = mockMvc.perform(put("/ticket/{id}", savedTicket.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(ticketDtoToUpdate)));
//
//        // then
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.category", is(ticketDtoToUpdate.getCategory().toString())));
//    }

    @Test
    void getTicketListByEventId() throws Exception {
        // given
        Event savedEvent = eventRepository.save(buildDefaultEvent());

        List<Ticket> ticketList = List.of(buildDefaultTicketWithCustomEventId(savedEvent.getId()),
                buildDefaultTicketWithCustomEventId(savedEvent.getId()),
                buildDefaultTicketWithCustomEventId(savedEvent.getId()));
        List<Ticket> savedTicketList = ticketRepository.saveAll(ticketList);

        // when
        ResultActions response =
                mockMvc.perform(get("/ticket/search?eventId={eventId}", savedEvent.getId()));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(savedTicketList.size())));
    }

    @Test
    void getTicketList() throws Exception {
        // given
        Event savedEvent = eventRepository.save(buildDefaultEvent());

        List<Ticket> ticketList = List.of(buildDefaultTicketWithCustomEventId(savedEvent.getId()),
                buildDefaultTicketWithCustomEventId(savedEvent.getId()),
                buildDefaultTicketWithCustomEventId(savedEvent.getId()));
        ticketRepository.saveAll(ticketList);

        // when
        ResultActions response = mockMvc.perform(get("/ticket"));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(3)));
    }

    @Test
    void deleteTicket() throws Exception {
        // given
        Event savedEvent = eventRepository.save(buildDefaultEvent());
        Ticket savedTicket = ticketRepository.save(buildDefaultTicketWithCustomEventId(savedEvent.getId()));

        // when
        ResultActions response = mockMvc.perform(delete("/ticket/{id}", savedTicket.getId()));

        // then
        response.andExpect(status().isOk())
                .andDo(print());
    }

}