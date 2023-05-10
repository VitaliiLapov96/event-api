package com.event.data.generator.controller.api;

import com.event.data.generator.model.Event;
import com.event.data.generator.model.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface TicketGeneratorApi {

    @PostMapping("/ticket")
    @ResponseStatus(HttpStatus.OK)
    List<Ticket> generateTicketList(@RequestParam @Min(1) int amount, @RequestBody Event event) throws NoSuchAlgorithmException;

}
