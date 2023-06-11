package com.event.core.client;


import com.event.core.dto.EventDto;
import com.event.core.dto.TicketDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import java.util.List;

@FeignClient(url = "http://localhost:8081/data-generator", name = "data-generator")
public interface DataGeneratorClient {

    @GetMapping("/event")
    List<EventDto> generateEventList(@RequestParam @Min(1) int amount);

    @PostMapping("/ticket")
    List<TicketDto> generateTicketList(@RequestParam @Min(1) int amount, @RequestBody EventDto event);

}
