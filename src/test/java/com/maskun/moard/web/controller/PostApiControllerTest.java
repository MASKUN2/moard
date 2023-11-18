package com.maskun.moard.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maskun.moard.web.dto.PostSaveDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
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
    void Post_등록되고_삭제된다() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();//responseEntity의 Json property를 가져오기 위한 Jackson 라이브러리의 클래스

        //given1
        String url = "http://localhost:"+testPort+"/api/version1.0/post";
        String title = "테스트제목";
        String content = "테스트내용";
        PostSaveDto dto = PostSaveDto.builder().title(title).content(content).build();

        //when1
        ResponseEntity<JsonNode> responseEntity = testRestTemplate.postForEntity(url, dto, JsonNode.class);

        //then1
        log.debug(responseEntity.getBody().toString());

        // JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
        //Long postIdSaved = jsonNode.get("postIdSaved").asLong();
        //위를 축약해서  ResponseEntity<String> 이 아닌  ResponseEntity<JsonNode> 로 받아 간소화했다. 아마 Spring의 Jackson Lib가 변환해주는 듯 하다.
        Long postIdSaved = responseEntity.getBody().get("postIdSaved").asLong();
        log.debug("postIdSaved : {}", postIdSaved);

        /*이 테스트는 external Web Client environment RestApiTest 이기 때문에 @Transaction은 Test를 외부 앱으로 인식하여 Propagation 하지 않는다.
        * 따라서 DB에 post는 insert되고 rollback이 수행되지 않는다. 따라서 가장 간단한 방법으로 반영사항을 취소하기 위해서 HttpDeleteRequest Api를 함께
        * 포함하게 되었다.*/

        //given2
        url = "http://localhost:"+testPort+"/api/version1.0/post/"+postIdSaved;

        //when2
        //testRestTemplate.delete()는 기본적으로 Return을 하지 않기 때문에 .exchange()를 사용했다. 대신 URLPathVariable을 사용하는 만큼 RequestBody는 null로 지정했다.
        ResponseEntity<JsonNode> responseEntity1 = testRestTemplate.exchange(url, HttpMethod.DELETE,null,JsonNode.class);

        //then2
        Long postIdDeleted = responseEntity1.getBody().get("postIdDeleted").asLong();
        log.debug("postIdDeleted : {}", postIdDeleted);
        assertThat(postIdDeleted).isEqualTo(postIdSaved);

    }
}