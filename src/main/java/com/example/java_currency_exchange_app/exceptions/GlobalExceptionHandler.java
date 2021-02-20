package com.example.java_currency_exchange_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleDefaultException(Exception ex) {
        ex.printStackTrace();
        return new ErrorResponse();
    }

    @ExceptionHandler({
        MethodArgumentTypeMismatchException.class,
        WebClientResponseException.BadRequest.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMethodArgumentTypeMismatchException(Exception ex) {
        ex.printStackTrace();
        return new ErrorResponse(new InvalidInputException());
    }
}
