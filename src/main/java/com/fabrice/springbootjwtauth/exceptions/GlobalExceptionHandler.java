package com.fabrice.springbootjwtauth.exceptions;

import com.fabrice.springbootjwtauth.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    //global exceptions => reformatting spring boot exception response
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalExceptions(Exception exception){
        String message  =  exception.getMessage();
        Integer statusCode = HttpStatus.BAD_REQUEST.value();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatusCode(statusCode);
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
