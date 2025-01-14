package com.itheima.exception;

public class ClazzNotEmptyException extends RuntimeException {
    public ClazzNotEmptyException(String message) {
        super(message);
    }
}