package com.matheus.mytwitter.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PasswordDoesNotMatchException extends ResponseStatusException {
    public PasswordDoesNotMatchException(){
        super(
                HttpStatus.BAD_REQUEST,
                "The password and the password confirmation do not match"
        );
    }
}
