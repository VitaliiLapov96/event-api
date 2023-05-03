package com.event.core.controller;

import com.event.core.controller.api.RegistrationApi;
import com.event.core.dto.AccountDto;
import com.event.core.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController implements RegistrationApi {

    private final AccountService accountService;

    @Override
    public String registration(AccountDto accountDto) {
        return accountService.register(accountDto);
    }

}
