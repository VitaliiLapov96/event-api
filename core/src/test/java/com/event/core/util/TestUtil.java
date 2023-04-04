package com.event.core.util;

import com.event.core.dto.EventDto;
import com.event.core.dto.TicketDto;
import com.event.core.model.Category;
import com.event.core.model.Event;
import com.event.core.model.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public final class TestUtil {

    public static EventDto buildDefaultEventDto() {
        return new EventDto(){
            {
                setId(1);
                setName("Opera");
                setDescription("Big opera concert");
                setPrice(100.0);
                setPlace("Lviv, Svobody Ave. 28");
                setDate(LocalDate.now());
            }
        };
    }

    public static Event buildDefaultEvent() {
        return new Event(){
            {
                setId(1);
                setName("Opera");
                setDescription("Big opera concert");
                setPrice(100.0);
                setPlace("Lviv, Svobody Ave. 28");
                setDate(LocalDate.now());
            }
        };
    }

    public static EventDto buildDefaultEventDtoWithCustomId(Long id) {
        return new EventDto(){
            {
                setId(id);
                setName("Opera");
                setDescription("Big opera concert");
                setPrice(100.0);
                setPlace("Lviv, Svobody Ave. 28");
                setDate(LocalDate.now());
            }
        };
    }

    public static Event buildDefaultEventWithCustomId(Long id) {
        return new Event(){
            {
                setId(id);
                setName("Opera");
                setDescription("Big opera concert");
                setPrice(100.0);
                setPlace("Lviv, Svobody Ave. 28");
                setDate(LocalDate.now());
            }
        };
    }

    public static TicketDto buildDefaultTicketDto() {
        return new TicketDto(){
            {
                setId(1);
                setCategory(Category.STANDARD);
                setEvent(buildDefaultEventDto());
                setCreatedDate(LocalDateTime.now());
            }
        };
    }

    public static Ticket buildDefaultTicket() {
        return new Ticket(){
            {
                setId(1L);
                setCategory(Category.STANDARD);
                setEvent(new Event());
                setCreatedDate(LocalDateTime.now());
            }
        };
    }

    public static TicketDto buildDefaultTicketDtoWithCustomId(Long id) {
        return new TicketDto(){
            {
                setId(id);
                setCategory(Category.STANDARD);
                setEvent(buildDefaultEventDto());
                setCreatedDate(LocalDateTime.now());
            }
        };
    }

    public static Ticket buildDefaultTicketWithCustomId(Long id) {
        return new Ticket(){
            {
                setId(id);
                setCategory(Category.STANDARD);
                setEvent(new Event());
                setCreatedDate(LocalDateTime.now());
            }
        };
    }

}
