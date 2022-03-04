package com.matheus.mytwitter.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFollowingException extends ResponseStatusException {
    public NotFollowingException() {
        super(HttpStatus.BAD_REQUEST, "You cannot see this profile because this user has a private account and you are not following this user");
    }
}
