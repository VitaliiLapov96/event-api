package com.event.data.generator.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Account {

    private String email;
    private String password;

    private Role role;

    private List<Ticket> tickets;

}
