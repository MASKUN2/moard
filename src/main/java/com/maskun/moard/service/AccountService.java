package com.maskun.moard.service;

import com.maskun.moard.domain.account.Account;
import com.maskun.moard.domain.account.AccountJpaRepository;
import com.maskun.moard.web.dto.LoginDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {
    //롬복 생성자. 의존 빈 자동주입
    private final AccountJpaRepository repository;

    @Transactional
    public boolean login(LoginDto loginDto, HttpSession session){
        String id = loginDto.getAccountId();
        String pw = loginDto.getAccountPw();

        Account account = repository.login(id, pw).orElse(null);
        if(account != null){
            log.debug("로그인성공");
            return true;
        }else {
            log.debug("로그인실패");
            return false;
        }
    }
}
