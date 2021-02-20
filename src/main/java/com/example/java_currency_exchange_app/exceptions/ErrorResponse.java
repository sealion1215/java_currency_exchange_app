package com.example.java_currency_exchange_app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    public ErrorResponse() {
        message = "Internal Server Error";
    }

    public ErrorResponse(Exception ex) {
        message = ex.getMessage();
    }
}
