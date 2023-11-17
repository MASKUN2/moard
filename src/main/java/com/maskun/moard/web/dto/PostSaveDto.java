package com.maskun.moard.web.dto;

import com.maskun.moard.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class PostSaveDto {
    private String title;
    private String content;

    public Post toEntity(){
        return Post.builder().title()
    }
}
