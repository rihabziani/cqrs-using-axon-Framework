package com.example.comptecqrses.commonApi.exceptions;

public class BalanceNotSufficentException extends RuntimeException {
    public BalanceNotSufficentException(String message) {
        super(message);
    }
}
