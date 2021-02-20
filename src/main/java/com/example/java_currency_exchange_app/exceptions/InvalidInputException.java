package com.example.java_currency_exchange_app.exceptions;

import lombok.Getter;

@Getter
public class InvalidInputException extends RuntimeException{
    public InvalidInputException() {
        super("Invalid Input");
    }
}
