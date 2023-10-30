package com.maskun.moard.domain.account;

import com.maskun.moard.web.dto.LoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    public void DaO_로그인한다(){
       /* LoginDto loginDto = new LoginDto();
        loginDto.setAccountId("test_user");
        loginDto.setAccountId("1234");
        Account account = new Account(loginDto);
        String result = accountDao.login(account);
        System.out.println(result);
        assertThat(result).isEqualTo("test_user");
        */
    }



}