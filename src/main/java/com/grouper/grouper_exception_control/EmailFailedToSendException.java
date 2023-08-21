package com.grouper.grouper_exception_control;

public class EmailFailedToSendException extends Throwable {

    public EmailFailedToSendException() {
        super("Email failed to send to the recipient");
    }
}
