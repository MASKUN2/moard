package com.maskun.moard.service;

import com.maskun.moard.domain.account.Account;
import com.maskun.moard.web.dto.LoginDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {

    @Transactional
    public boolean login(LoginDto loginDto){
        Account account = new Account(loginDto);
    return false;
    }
}
