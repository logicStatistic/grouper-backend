package com.grouper.grouper_exception_control;

public class EmailAlreadyTakenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyTakenException() {
        super("Email already taken");
    }
}
