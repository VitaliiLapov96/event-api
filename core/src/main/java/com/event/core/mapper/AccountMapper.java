package com.event.core.mapper;

import com.event.core.dto.AccountDto;
import com.event.core.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account accountDtoMapToAccount(AccountDto accountDto);

    AccountDto accountMapToAccountDto(Account account);

}
