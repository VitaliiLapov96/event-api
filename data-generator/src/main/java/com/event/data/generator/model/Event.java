package com.event.data.generator.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
public class Event {

    private String name;
    private String description;
    private String place;

    private double price;

    private List<Ticket> tickets;

    private LocalDate date;

}
