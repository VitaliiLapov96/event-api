package com.event.core.controller;

import com.event.core.dto.EventDto;
import com.event.core.repository.EventRepository;
import com.event.core.service.EventService;
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

import java.time.LocalDate;

import static com.event.core.util.TestUtil.buildDefaultEventDto;
import static com.event.core.util.TestUtil.buildDefaultEventDtoWithCustomId;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        eventRepository.deleteAll();
    }

    @Test
    void createEvent() throws Exception {
        // given
        EventDto expectedEventDto = buildDefaultEventDto();

        // when
        ResultActions response = mockMvc.perform(post("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedEventDto)));

        // then
        response.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(expectedEventDto.getName())))
                .andExpect(jsonPath("$.description", is(expectedEventDto.getDescription())))
                .andExpect(jsonPath("$.place", is(expectedEventDto.getPlace())))
                .andExpect(jsonPath("$.price", is(expectedEventDto.getPrice())));
    }

    @Test
    void getEvent() throws Exception {
        // given
        EventDto expectedEventDto = buildDefaultEventDto();
        eventService.create(expectedEventDto);

        // when
        ResultActions response = mockMvc.perform(get("/event/{id}", expectedEventDto.getId()));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(expectedEventDto.getName())))
                .andExpect(jsonPath("$.description", is(expectedEventDto.getDescription())))
                .andExpect(jsonPath("$.place", is(expectedEventDto.getPlace())))
                .andExpect(jsonPath("$.price", is(expectedEventDto.getPrice())));
    }

    @Test
    void getInvalidEvent() throws Exception {
        // given

        // when
        ResultActions response = mockMvc.perform(get("/event/{id}", 1));

        // then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void updateEvent() throws Exception {
        // given
        EventDto expectedEventDto = buildDefaultEventDto();
        eventService.create(expectedEventDto);

        EventDto eventDtoToUpdate = buildDefaultToUpdateEventDto();

        // when
        ResultActions response = mockMvc.perform(put("/event/{id}", expectedEventDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDtoToUpdate)));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(eventDtoToUpdate.getName())))
                .andExpect(jsonPath("$.description", is(eventDtoToUpdate.getDescription())))
                .andExpect(jsonPath("$.place", is(eventDtoToUpdate.getPlace())))
                .andExpect(jsonPath("$.price", is(eventDtoToUpdate.getPrice())));
    }

    @Test
    void getEventList() throws Exception {
        // given
        EventDto expectedEventDto1 = buildDefaultEventDtoWithCustomId(1L);
        EventDto expectedEventDto2 = buildDefaultEventDtoWithCustomId(2L);
        EventDto expectedEventDto3 = buildDefaultEventDtoWithCustomId(3L);

        eventService.create(expectedEventDto1);
        eventService.create(expectedEventDto2);
        eventService.create(expectedEventDto3);

        // when
        ResultActions response = mockMvc.perform(get("/event"));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(3)));
    }

    @Test
    void deleteEvent() throws Exception {
        // given
        EventDto expectedEventDto = buildDefaultEventDto();
        eventService.create(expectedEventDto);

        // when
        ResultActions response = mockMvc.perform(delete("/event/{id}", expectedEventDto.getId()));

        // then
        response.andExpect(status().isOk())
                .andDo(print());
    }

    private static EventDto buildDefaultToUpdateEventDto() {
        return new EventDto() {
            {
                setId(1L);
                setName("Ballet");
                setDescription("Big ballet concert");
                setPrice(100.0);
                setPlace("Kyiv, Volodymyrska Street, 50");
                setDate(LocalDate.now());
            }
        };
    }

}