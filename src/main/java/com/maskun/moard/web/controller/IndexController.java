package com.maskun.moard.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    @GetMapping("/")
    public String index( Model model){
        log.debug("debug log={}", "index 호출");
        return "index";
    }

    @GetMapping("/login")
    public String login( Model model){
        log.debug("debug log={}", "로그인 호출");
        return "login";
    }


}
