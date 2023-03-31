package com.event.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventDto {

    private long id;

    private String name;
    private String description;
    private String place;

    private double price;

    private LocalDate date;

}
