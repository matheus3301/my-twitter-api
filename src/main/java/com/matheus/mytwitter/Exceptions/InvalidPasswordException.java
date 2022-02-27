package com.matheus.mytwitter.Exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("Invalid Password");
    }
}
