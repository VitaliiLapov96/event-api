package com.event.core.controller;

import com.event.core.EventApplication;
import com.event.core.dto.EventDto;
import com.event.core.model.Event;
import com.event.core.repository.EventRepository;
import com.event.core.security.config.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.event.core.util.TestUtil.buildDefaultEvent;
import static com.event.core.util.TestUtil.buildDefaultEventDto;
import static com.event.core.util.TestUtil.buildDefaultToUpdateEventDto;
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
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        eventRepository.deleteAll();
    }

//    @Test
//    void createEvent() throws Exception {
//        // given
//        EventDto expectedEventDto = buildDefaultEventDto();
//
//        // when
//        ResultActions response = mockMvc.perform(post("/event")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(expectedEventDto)));
//
//        // then
//        response.andExpect(status().isCreated())
//                .andDo(print())
//                .andExpect(jsonPath("$.name", is(expectedEventDto.getName())))
//                .andExpect(jsonPath("$.description", is(expectedEventDto.getDescription())))
//                .andExpect(jsonPath("$.place", is(expectedEventDto.getPlace())))
//                .andExpect(jsonPath("$.price", is(expectedEventDto.getPrice())));
//    }

    @Test
    void getEvent() throws Exception {
        // given
        Event expectedEvent = eventRepository.save(buildDefaultEvent());

        // when
        ResultActions response = mockMvc.perform(get("/event/{id}", expectedEvent.getId()));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(expectedEvent.getName())))
                .andExpect(jsonPath("$.description", is(expectedEvent.getDescription())))
                .andExpect(jsonPath("$.place", is(expectedEvent.getPlace())))
                .andExpect(jsonPath("$.price", is(expectedEvent.getPrice())));
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
        Event expectedEvent = eventRepository.save(buildDefaultEvent());

        EventDto eventDtoToUpdate = buildDefaultToUpdateEventDto();

        // when
        ResultActions response = mockMvc.perform(put("/event/{id}", expectedEvent.getId())
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
        List<Event> eventList = List.of(buildDefaultEvent(),
                buildDefaultEvent(), buildDefaultEvent());
        eventRepository.saveAll(eventList);

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
        Event savedEvent = eventRepository.save(buildDefaultEvent());

        // when
        ResultActions response = mockMvc.perform(delete("/event/{id}", savedEvent.getId()));

        // then
        response.andExpect(status().isOk())
                .andDo(print());
    }

}