package com.maskun.moard.web.controller;

import com.maskun.moard.web.dto.PostSaveDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class PostApiControllerTest {
    @LocalServerPort
    private int testPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void Post_등록된다(){
        //given
        String url = "http://localhost:"+testPort+"/api/version1.0/post";
        String title = "테스트제목";
        String content = "테스트내용";
        PostSaveDto dto = PostSaveDto.builder().title(title).content(content).build();

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, dto, Long.class);

        //then
        String responseBody = responseEntity.getBody().toString();
        log.info(responseBody);
    }
}