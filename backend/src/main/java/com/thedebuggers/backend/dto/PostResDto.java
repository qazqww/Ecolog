package com.thedebuggers.backend.dto;

import com.thedebuggers.backend.domain.entity.Post;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class PostResDto {
    private long no;
    private String title;
    private String content;
    private String image;
    private LocalDateTime createdAt;
    private boolean isOpen;
    private long likeCount;
    private ProfileResDto writer;

    public static PostResDto of(Post post) {
        return PostResDto.builder()
                .no(post.getNo())
                .title(post.getTitle())
                .content(post.getContent())
                .image(post.getImage())
                .createdAt(post.getCreatedAt())
                .isOpen(post.isOpen())
                .likeCount(post.getLikeCount())
                .writer(ProfileResDto.of(post.getUser()))
                .build();
    }
}
