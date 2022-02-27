package com.matheus.mytwitter.ErrorHandling;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {PSQLException.class})
    public ResponseEntity<ErrorResponse> handlePSQLException(PSQLException exception, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setPath(request.getRequestURI().substring(request.getContextPath().length()));
        errorResponse.setStatus((short) HttpStatus.BAD_REQUEST.value());

        String[] detail = exception.getMessage().split("\n");
        errorResponse.setError(detail[0].trim().substring(7).split("\"")[0].trim());

        errorResponse.setMessage(
                detail[1]
                .trim()
                .substring(8)
                .split("=")[1]
                .replace("(","")
                .replace(")", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
