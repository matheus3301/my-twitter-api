package com.matheus.mytwitter.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlreadyFollowingException extends ResponseStatusException {
    public AlreadyFollowingException() {
        super(HttpStatus.BAD_REQUEST, "You are already following this user");
    }
}
