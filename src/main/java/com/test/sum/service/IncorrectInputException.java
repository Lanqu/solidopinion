package com.test.sum.service;

public class IncorrectInputException extends Exception {
    public IncorrectInputException(final String message) {
        super(message);
    }
}
