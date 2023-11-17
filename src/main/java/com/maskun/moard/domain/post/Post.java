package com.maskun.moard.domain.post;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(columnDefinition = "TINYTEXT")
    private String postContent;
    @Column(columnDefinition = "TEXT")
    private String postTitle;

    @Builder
    private Post(String content, String title) {
        this.postContent = content;
        this.postTitle = title;
    }
}
