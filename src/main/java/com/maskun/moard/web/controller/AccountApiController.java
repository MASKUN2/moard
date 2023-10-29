package com.maskun.moard.web.controller;

import com.maskun.moard.service.AccountService;
import com.maskun.moard.web.dto.LoginDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;


@Slf4j //Logger log 를 자동으로 생성해준다.
@RequiredArgsConstructor
@RestController // 이전에 배웠던 책에서는 Controller로 명명했지만 해당 API는 RESTful한 json 데이터타입 환경에서 작동되었으면 해서 RestController로 만들었다. 또한 앤드포인트에서 메세지를 json 포맷으로 클라이언트로 보내고 싶었기 때문에 esponseEntity.ok(Collections.singletonMap을 사용했다. 만약 @RestController를 사용하지 않으면 해당 응답 객체는 json 포멧으로 보내지지 못한다. 둘은 세트이다.
public class AccountApiController {

    private final AccountService accountService;

    @PostMapping("/api/ver1/login")
    public ResponseEntity<Object> login (@RequestBody LoginDto loginDto){
        log.info("info log = {}",loginDto.getUserId()+"<--> "+loginDto.getUserPassword());

        accountService.login(loginDto);

        return ResponseEntity.ok(Collections.singletonMap("message", "로그인완료"));
    }
}
