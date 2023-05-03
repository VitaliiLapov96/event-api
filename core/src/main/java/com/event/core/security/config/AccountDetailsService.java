package com.event.core.security.config;

import com.event.core.exception.EntityNotFoundException;
import com.event.core.exception.EventCoreException;
import com.event.core.model.Account;
import com.event.core.repository.AccountRepository;
import com.event.core.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountDetailsService implements UserDetailsService {

    public static final String ACCOUNT_IS_BLOCKED_ERROR_MSG = "Account is blocked";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (loginAttemptService.isBlocked()) {
            throw new EventCoreException(ACCOUNT_IS_BLOCKED_ERROR_MSG);
        }

        Optional<Account> account = accountRepository.findByEmail(email);

        return account.map(UserDetailsInfo::new)
                .orElseThrow(() -> new EntityNotFoundException(Account.class));
    }

}
