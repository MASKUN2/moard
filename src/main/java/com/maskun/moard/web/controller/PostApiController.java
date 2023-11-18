package com.maskun.moard.web.controller;

import com.maskun.moard.service.PostService;
import com.maskun.moard.web.dto.PostSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/version1.0")
public class PostApiController {
    private final PostService service;

    @PostMapping("/post")
    public ResponseEntity savePost(@RequestBody PostSaveDto dto){
        log.debug(dto.toString());
        Long SavedPostId = service.savePost(dto);
        Map<String,Object> responseBodyMap = Map.of("postIdSaved", SavedPostId);  // simple <10 map creator(java9)
        if (SavedPostId != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseBodyMap);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBodyMap);
        }
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity deletePost(@PathVariable Long id) {
        log.debug("id {}", id);
        Long postIdDeleted = service.deletePost(id);
        Map<String,Object> responseBodyMap = Map.of("postIdDeleted", postIdDeleted);
        if (postIdDeleted != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseBodyMap);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBodyMap);
        }


    }
}
