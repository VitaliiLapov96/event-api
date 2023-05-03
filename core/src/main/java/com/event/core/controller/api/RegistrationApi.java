package com.event.core.controller.api;

import com.event.core.dto.AccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/registration")
public interface RegistrationApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    String registration(@RequestBody AccountDto accountDto);

}
