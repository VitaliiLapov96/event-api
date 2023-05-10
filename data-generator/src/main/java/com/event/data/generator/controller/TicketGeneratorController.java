package com.event.data.generator.controller;

import com.event.data.generator.controller.api.TicketGeneratorApi;
import com.event.data.generator.model.Event;
import com.event.data.generator.model.Ticket;
import com.event.data.generator.util.DataGeneratorUtil;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class TicketGeneratorController implements TicketGeneratorApi {

    @Override
    public List<Ticket> generateTicketList(int amount, Event event) throws NoSuchAlgorithmException {
        return DataGeneratorUtil.generateTicketList(amount, event);
    }

}
