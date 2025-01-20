package com.itheima.exception;

public class DeleteException extends RuntimeException {
    public DeleteException() {
    }
    public DeleteException(String message) {
        super(message);
    }
}
