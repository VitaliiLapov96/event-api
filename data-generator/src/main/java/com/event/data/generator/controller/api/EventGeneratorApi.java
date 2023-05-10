package com.event.data.generator.controller.api;

import com.event.data.generator.model.Event;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.Min;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface EventGeneratorApi {

    @GetMapping("/event")
    @ResponseStatus(HttpStatus.OK)
    List<Event> generateEventList(@RequestParam @Min(1) int amount) throws NoSuchAlgorithmException;

}
