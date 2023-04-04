package com.event.core.controller;

import com.event.core.dto.EventDto;
import com.event.core.dto.TicketDto;
import com.event.core.model.Category;
import com.event.core.repository.EventRepository;
import com.event.core.repository.TicketRepository;
import com.event.core.service.EventService;
import com.event.core.service.TicketService;
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

import java.time.LocalDateTime;

import static com.event.core.util.TestUtil.buildDefaultEventDto;
import static com.event.core.util.TestUtil.buildDefaultTicketDto;
import static com.event.core.util.TestUtil.buildDefaultTicketDtoWithCustomId;
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
@AutoConfigureMockMvc
class TicketControllerTest {

    private static final EventDto EVENT_DTO = buildDefaultEventDto();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketService ticketService;
    @Autowired
    private EventService eventService;

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
        TicketDto expectedTicketDto = buildDefaultTicketDto();
        eventService.create(EVENT_DTO);

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
        TicketDto expectedTicketDto = buildDefaultTicketDto();

        eventService.create(EVENT_DTO);
        ticketService.create(expectedTicketDto);

        // when
        ResultActions response = mockMvc.perform(get("/ticket/{id}", expectedTicketDto.getId()));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.category", is(expectedTicketDto.getCategory().toString())));
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

    @Test
    void updateTicket() throws Exception {
        // given
        TicketDto expectedTicketDto = buildDefaultTicketDto();

        eventService.create(EVENT_DTO);
        ticketService.create(expectedTicketDto);

        TicketDto ticketDtoToUpdate = buildDefaultToUpdateTicketDto();

        // when
        ResultActions response = mockMvc.perform(put("/ticket/{id}", expectedTicketDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticketDtoToUpdate)));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.category", is(ticketDtoToUpdate.getCategory().toString())));
    }

    @Test
    void getTicketList() throws Exception {
        // given
        TicketDto expectedTicketDto1 = buildDefaultTicketDtoWithCustomId(1L);
        TicketDto expectedTicketDto2 = buildDefaultTicketDtoWithCustomId(2L);
        TicketDto expectedTicketDto3 = buildDefaultTicketDtoWithCustomId(3L);

        eventService.create(EVENT_DTO);

        ticketService.create(expectedTicketDto1);
        ticketService.create(expectedTicketDto2);
        ticketService.create(expectedTicketDto3);

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
        TicketDto expectedTicketDto = buildDefaultTicketDto();

        eventService.create(EVENT_DTO);
        ticketService.create(expectedTicketDto);

        // when
        ResultActions response = mockMvc.perform(delete("/ticket/{id}", expectedTicketDto.getId()));

        // then
        response.andExpect(status().isOk())
                .andDo(print());
    }

    private static TicketDto buildDefaultToUpdateTicketDto() {
        return new TicketDto() {
            {
                setId(1);
                setCategory(Category.PREMIUM);
                setEvent(EVENT_DTO);
                setCreatedDate(LocalDateTime.now());
            }
        };
    }

}