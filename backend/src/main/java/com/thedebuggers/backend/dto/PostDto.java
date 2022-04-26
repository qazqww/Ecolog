package com.thedebuggers.backend.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class PostDto {
    private String title;
    private String content;
    private String image;
    private LocalDate createdAt;
    private boolean isOpen;
    private int userNo;
}
