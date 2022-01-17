package com.example.healthcare.util.exceptionhandler.exceptions;

public class WrongCodeException extends Exception {

    public WrongCodeException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws WrongCodeException {
        if (ex) {
            throw new WrongCodeException(message);
        }
    }
}


