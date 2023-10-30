package com.maskun.moard.domain.account;

import com.maskun.moard.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RequiredArgsConstructor
@Repository
public class AccountDao {
    //롬복의 생성자 자동생성, 자동주입
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public String login(LoginDto loginDto)throws EmptyResultDataAccessException{
        //아이디와 비밀번호가 일치하는 아이디를 가져옴
        String sql = "SELECT account_id FROM accounts WHERE account_id = :accountId AND account_pw = PASSWORD(:accountPw)";
        Map<String,Object> nameParameters = new HashMap<>();
        nameParameters.put("accountId", loginDto.getAccountId());
        nameParameters.put("accountPw", loginDto.getAccountPw());
        //일치하는 아이디를 가져와서 리턴하고 예외가 있으면 던짐
        String result = namedParameterJdbcTemplate.queryForObject(sql,nameParameters,String.class);

        return result;
    }
}
