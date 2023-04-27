package com.event.core.dto;

import com.event.core.model.Category;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TicketDto {

    private long id;

    private EventDto event;

    private Category category;

    private LocalDateTime createdDate;

}
