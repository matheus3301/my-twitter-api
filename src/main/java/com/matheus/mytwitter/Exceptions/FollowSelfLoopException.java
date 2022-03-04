package com.matheus.mytwitter.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FollowSelfLoopException extends ResponseStatusException {
    public FollowSelfLoopException() {
        super(HttpStatus.BAD_REQUEST, "You cannot follow yourself");
    }
}
