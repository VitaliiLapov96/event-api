package com.event.core.dto;

import com.event.core.model.Category;
import com.event.core.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketDto {

    private long id;

    private Event event;

    private Category category;

    private LocalDateTime createdDate;

}
