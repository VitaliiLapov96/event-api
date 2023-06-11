package com.event.core.service.impl;

import com.event.core.dto.AccountDto;
import com.event.core.mapper.AccountMapper;
import com.event.core.model.Account;
import com.event.core.model.Role;
import com.event.core.repository.AccountRepository;
import com.event.core.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final String EXIST_USER = "User is already registered";
    private static final String REGISTERED_USER = "User is registered successfully";

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(AccountDto accountDto) {
        Account account = AccountMapper.INSTANCE.accountDtoMapToAccount(accountDto);

        account.setPassword(passwordEncoder.encode(account.getPassword()));

        if (account.getEmail().equals(Role.ADMIN.getValue().toLowerCase())) {
            account.setRole(Role.ADMIN);
        } else {
            account.setRole(Role.USER);
        }

        Account savedAccount = accountRepository.save(account);
        log.info(REGISTERED_USER);
        log.debug("{}: [{}]", REGISTERED_USER, savedAccount);

        return REGISTERED_USER;
    }

}
