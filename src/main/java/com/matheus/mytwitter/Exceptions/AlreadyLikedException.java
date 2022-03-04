package com.matheus.mytwitter.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlreadyLikedException extends ResponseStatusException {
    public AlreadyLikedException() {
        super(HttpStatus.BAD_REQUEST, "You already liked this tweet");
    }
}
