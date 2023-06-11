package com.event.data.generator.controller;

import com.event.data.generator.controller.api.EventGeneratorApi;
import com.event.data.generator.model.Event;
import com.event.data.generator.util.DataGeneratorUtil;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class EventGeneratorController implements EventGeneratorApi {

    @Override
    public List<Event> generateEventList(int amount) throws NoSuchAlgorithmException {
        return DataGeneratorUtil.generateEventList(amount);
    }

}
