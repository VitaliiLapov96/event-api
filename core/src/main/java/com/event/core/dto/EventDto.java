package com.event.core.dto;

import com.event.core.model.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EventDto {

    private long id;

    private String name;
    private String description;
    private String place;

    private double price;

    private List<Ticket> tickets;

    private LocalDate date;

}
