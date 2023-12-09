package com.fabrice.springbootjwtauth.exceptions;

import com.fabrice.springbootjwtauth.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    //handle not found exception
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) //response status code
    public ErrorResponse handleNotFoundException(NotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        return  errorResponse;
    }


    // global exceptions => reformatting spring boot exception response
    // for all remaining kind of errors which can be found within our app
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
