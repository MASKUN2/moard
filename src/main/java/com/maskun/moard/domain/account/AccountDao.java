package com.maskun.moard.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class AccountDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public String login(Account account){
        String sql = "SELECT account_id FROM accounts WHERE account_id = :accountId AND account_pw = PASSWORD(:accountPw)";
        Map<String,Object> nameParameters = new HashMap<>();
        nameParameters.put("accountId",account.getAccountId());
        nameParameters.put("accountPw", account.getAccountPw());
        String result = namedParameterJdbcTemplate.queryForObject(sql,nameParameters,String.class);
        return result;
    }
}
