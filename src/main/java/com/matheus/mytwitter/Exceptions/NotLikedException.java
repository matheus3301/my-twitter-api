package com.matheus.mytwitter.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotLikedException extends ResponseStatusException {
    public NotLikedException() {
        super(HttpStatus.BAD_REQUEST, "You did not like this tweet");
    }
}
