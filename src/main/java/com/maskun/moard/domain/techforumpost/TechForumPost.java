package com.maskun.moard.domain.techforumpost;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TechForumPost {
    private Long postId;
    private String accountId;
    private String title;
    private String content;
    private Long view;
    private Long likes;
    private Long dislikes;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;


}
