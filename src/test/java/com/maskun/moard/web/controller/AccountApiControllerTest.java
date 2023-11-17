package com.maskun.moard.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maskun.moard.web.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class AccountApiControllerTest {
    @LocalServerPort
    private int testPort;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void login성공한다() throws JsonProcessingException {
        //given
        String id = "test_id";
        String pw = "1234";
        String url = "http://localhost:"+testPort+"/api/version1.0/account/login";
        //when
        LoginDto dto = LoginDto.builder().accountId(id).accountPw(pw).build();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, dto, String.class);
        log.info(responseEntity.getBody().toString());
        //then
        JsonNode jsonNode  = objectMapper.readTree(responseEntity.getBody());
        HttpStatusCode responseStatus = responseEntity.getStatusCode();
        String serverMessage = jsonNode.get("message").asText();
        assertThat(serverMessage).isEqualTo("로그인완료");
        assertThat(responseStatus).isEqualTo(HttpStatus.OK);
    }
}