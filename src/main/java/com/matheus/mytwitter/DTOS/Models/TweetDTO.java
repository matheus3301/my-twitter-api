package com.matheus.mytwitter.DTOS.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TweetDTO {
    private Long id;
    private String message;
    private String authorUsername;
    private String authorName;
    private LocalDateTime createdAt;
    private Long likes;
    private Long replies;

    public TweetDTO(){
        this.likes = 0L;
        this.replies = 0L;
    }
}
