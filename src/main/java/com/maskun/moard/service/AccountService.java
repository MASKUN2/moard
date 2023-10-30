package com.maskun.moard.service;

import com.maskun.moard.domain.account.AccountDao;
import com.maskun.moard.web.dto.LoginDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {
    //롬복 생성자. 의존 빈 자동주입
    private final AccountDao accountDao;

    @Transactional
    public boolean login(LoginDto loginDto, HttpSession session){
        String queryResult; // 쿼리 결과를 받을 변수
        try {
            //DAO로 부터 로그인이 성공하면 해당 AccountId를 문자열로 받음
            queryResult = accountDao.login(loginDto);
        }catch (EmptyResultDataAccessException e){
            //실패하면 EmptyResultDataAccessException를 넘겨받고 로깅한다.
            log.info("info log = {}", "로그인정보가 일치하지 않습니다.");
            //로그인실패 (false) 리턴
            return false;
        }
        //성공했다면 로그인 성공한 아이디를 세션에 loggedInId로 담고 true를 리턴
        String loginSuccessId = queryResult;
        session.setAttribute("loggedInId", loginSuccessId);
        return true;
    }
}
