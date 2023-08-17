package com.grouper.grouper_exception_control;

public class UserDoesNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserDoesNotExistException() {
        super("This user does not exist");
    }
}
