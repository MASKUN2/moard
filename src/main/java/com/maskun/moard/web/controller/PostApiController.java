package com.maskun.moard.web.controller;

import com.maskun.moard.service.PostService;
import com.maskun.moard.web.dto.PostSaveDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/version1.0")
public class PostApiController {
    private final PostService service;

    @PostMapping("/post")
    public Long savePost(PostSaveDto dto){
        return service.savePost(dto);
    }
}
