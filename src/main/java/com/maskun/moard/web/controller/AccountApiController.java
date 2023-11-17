package com.maskun.moard.web.controller;

import com.maskun.moard.service.AccountService;
import com.maskun.moard.web.dto.LoginDto;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;


@Slf4j //Logger log 를 자동으로 생성해준다.
@RequiredArgsConstructor
@RestController // 이전에 배웠던 책에서는 Controller로 명명했지만 해당 API는 RESTful한 json 데이터타입 환경에서 작동되었으면 해서 RestController로 만들었다. 또한 앤드포인트에서 메세지를 json 포맷으로 클라이언트로 보내고 싶었기 때문에 esponseEntity.ok(Collections.singletonMap을 사용했다. 만약 @RestController를 사용하지 않으면 해당 응답 객체는 json 포멧으로 보내지지 못한다. 둘은 세트이다.
@RequestMapping("/api/version1.0/account") // 해당 클래스의 요청 URL의 기본값을 적음
public class AccountApiController {
    //@RequiredArgsConstructor 통해 하나의 생성자를 만들고 빈의 의존관계를 확인한 스프링이 DI해줌
    private final AccountService accountService;


    @PostMapping("/login")
    public ResponseEntity<Object> login (@RequestBody LoginDto loginDto, HttpSession session){
        ResponseEntity<Object> responseEntity; // http code 및 메세지를 보낼 객체 생성

        boolean isLoginSuccess = accountService.login(loginDto, session); // 로그인의 성공여부를 받아옴

        if(isLoginSuccess){
            //성공하면 ok(200)http 코드와 메세지를 보냄
            responseEntity = ResponseEntity.ok(Collections.singletonMap("message", "로그인완료"));
            //Service에서 수행한 세션 addAttribute logging
            log.info("info log = session loggedInId = {}", session.getAttribute("sessionAccountId"));
        }else{
            //만약 실패하면 401 http code 전송
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "로그인정보가 일치하지 않습니다."));
            log.info("info log = {}", "로그인 실패 401");
        }
        return responseEntity;
    }
}
