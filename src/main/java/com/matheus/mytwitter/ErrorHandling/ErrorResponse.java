package com.matheus.mytwitter.ErrorHandling;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private Short status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(){
        this.timestamp = LocalDateTime.now();
    }
}
