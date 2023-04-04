package com.event.core.controller;

import com.event.core.controller.api.EventApi;
import com.event.core.dto.EventDto;
import com.event.core.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    private final EventService eventService;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        return eventService.create(eventDto);
    }

    @Override
    public EventDto getEvent(long id) {
        return eventService.findById(id);
    }

    @Override
    public void getCompletedEvent() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://seatgeek-seatgeekcom.p.rapidapi.com/taxonomies"))
                .header("X-RapidAPI-Key", "83779ba167msh7eb3e0600fb42a1p1a9979jsnc6696f665af6")
                .header("X-RapidAPI-Host", "seatgeek-seatgeekcom.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    @Override
    public EventDto updateEvent(long id, EventDto eventDto) {
        EventDto eventToUpdate = eventService.findById(id);

        eventToUpdate.setName(eventDto.getName());
        eventToUpdate.setDescription(eventDto.getDescription());
        eventToUpdate.setPlace(eventDto.getPlace());
        eventToUpdate.setPrice(eventDto.getPrice());

        return eventService.update(id, eventToUpdate);
    }

    @Override
    public List<EventDto> getEventList() {
        return eventService.findAll();
    }

    @Override
    public void deleteEvent(long id) {
        eventService.deleteById(id);
    }

}
