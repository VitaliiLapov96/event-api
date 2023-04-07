package com.event.core.util;

import com.event.core.dto.EventDto;
import com.event.core.dto.TicketDto;
import com.event.core.model.Category;
import com.event.core.model.Event;
import com.event.core.model.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class TestUtil {

    public static EventDto buildDefaultEventDto() {
        return new EventDto().setName("Opera")
                .setDescription("Big opera concert")
                .setPrice(100.0)
                .setPlace("Lviv, Svobody Ave. 28")
                .setDate(LocalDate.now());
    }

    public static Event buildDefaultEvent() {
        return new Event().setName("Opera")
                .setDescription("Big opera concert")
                .setPrice(100.0)
                .setPlace("Lviv, Svobody Ave. 28")
                .setDate(LocalDate.now());
    }

    public static EventDto buildDefaultEventDtoWithCustomId(long id) {
        return new EventDto().setId(id)
                .setName("Opera")
                .setDescription("Big opera concert")
                .setPrice(100.0)
                .setPlace("Lviv, Svobody Ave. 28")
                .setDate(LocalDate.now());
    }

    public static Event buildDefaultEventWithCustomId(long id) {
        return new Event().setId(id)
                .setName("Opera")
                .setDescription("Big opera concert")
                .setPrice(100.0)
                .setPlace("Lviv, Svobody Ave. 28")
                .setDate(LocalDate.now());
    }

    public static EventDto buildDefaultToUpdateEventDto() {
        return new EventDto().setName("Opera")
                .setDescription("Big opera concert")
                .setPrice(100.0)
                .setPlace("Lviv, Svobody Ave. 28")
                .setDate(LocalDate.now());
    }

    public static TicketDto buildDefaultTicketDto() {
        return new TicketDto().setCategory(Category.STANDARD)
                .setEvent(buildDefaultEventDto())
                .setCreatedDate(LocalDateTime.now());
    }

    public static TicketDto buildDefaultTicketDtoWithCustomEventId(long eventId) {
        return new TicketDto().setCategory(Category.STANDARD)
                .setEvent(buildDefaultEventDtoWithCustomId(eventId))
                .setCreatedDate(LocalDateTime.now());
    }

    public static TicketDto buildDefaultToUpdateTicketDtoWithCustomEventId(long eventId) {
        return new TicketDto().setCategory(Category.PREMIUM)
                .setEvent(buildDefaultEventDtoWithCustomId(eventId))
                .setCreatedDate(LocalDateTime.now());
    }

    public static Ticket buildDefaultTicket() {
        return new Ticket().setCategory(Category.STANDARD)
                .setEvent(buildDefaultEvent())
                .setCreatedDate(LocalDateTime.now());
    }

    public static Ticket buildDefaultTicketWithCustomEventId(long eventId) {
        return new Ticket().setCategory(Category.STANDARD)
                .setEvent(buildDefaultEventWithCustomId(eventId))
                .setCreatedDate(LocalDateTime.now());
    }

    public static Ticket buildDefaultTicketWithCustomId(Long id) {
        return new Ticket().setId(id)
                .setCategory(Category.STANDARD)
                .setEvent(new Event().setId(1))
                .setCreatedDate(LocalDateTime.now());
    }

}
