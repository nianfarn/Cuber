package com.samsol.cuber.controllers;

public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = 5073773259346834749L;

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
