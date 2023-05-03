package com.event.core.dto;

import com.event.core.model.Role;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountDto {

    private long id;

    private String email;
    private String password;

    private Role role;

}
