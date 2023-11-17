package com.maskun.moard.service;

import com.maskun.moard.domain.post.Post;
import com.maskun.moard.domain.post.PostJpaRepository;
import com.maskun.moard.web.dto.PostSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostJpaRepository repository;

    public Long savePost(PostSaveDto dto){
          Post savedPost = repository.save(dto.toEntity());

        return savedPost.getPostId();

    }

}
