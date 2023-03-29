package com.event.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private Category category;

    private LocalDateTime createdDate;

}
