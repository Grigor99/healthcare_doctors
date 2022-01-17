package com.example.healthcare.util.exceptionhandler.exceptions;

public class UnauthorizedException extends Exception {

    public UnauthorizedException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws UnauthorizedException {
        if (ex) {
            throw new UnauthorizedException(message);
        }
    }
}

