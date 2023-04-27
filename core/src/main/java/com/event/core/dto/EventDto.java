package com.event.core.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class EventDto {

    private long id;

    private String name;
    private String description;
    private String place;

    private double price;

    private LocalDate date;

}
