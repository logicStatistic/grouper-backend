package com.grouper.grouper_exception_control;

public class FileDoesNotExistException extends RuntimeException {

    public FileDoesNotExistException() {
        super("Credential files not found");
    }
}
