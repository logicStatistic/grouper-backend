package com.grouper.grouper_exception_control;

public class EmailFailedToSendException extends RuntimeException{

    public EmailFailedToSendException() {
        super("Email failed to send to the recipient");
    }
}
