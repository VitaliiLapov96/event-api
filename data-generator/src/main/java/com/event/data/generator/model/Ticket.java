package com.event.data.generator.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Ticket {

    private Event event;

    private Account account;

    private Category category;

    private LocalDateTime createdDate;

}
