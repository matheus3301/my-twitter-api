package com.matheus.mytwitter.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {
    public EntityNotFoundException(String entity) {
        super(HttpStatus.BAD_REQUEST, entity + " Not Found");
    }
}
