package com.maskun.moard.domain.account;

import com.maskun.moard.web.dto.LoginDto;
import lombok.Getter;

@Getter
public class Account {

    private final String accountId;
    private final String accountPw;

    public Account(LoginDto loginDto){
        this.accountId = loginDto.getUserId();
        this.accountPw = loginDto.getUserPassword();
    }

}
