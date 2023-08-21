package com.grouper.grouper_exception_control;

public class IncorrectVerificationCodeException extends RuntimeException {

    public IncorrectVerificationCodeException() {

        super("Incorrect Verification code");
    }
}
