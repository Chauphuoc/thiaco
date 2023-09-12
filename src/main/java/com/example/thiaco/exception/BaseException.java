package com.example.thiaco.exception;

import java.util.Map;

public abstract class BaseException extends RuntimeException {
    protected Integer status;
    protected Map<String, String> errors;

    private BaseException(String message, Map<String, String> errors, Integer status) {
        super(message);
        this.errors = errors;
        this.status = status;
    }

    public BaseException(String message, Integer status) {
        this(message, null, status);
    }

    public BaseException(Map<String, String> errors, Integer status) {
        this(null, errors, status);
    }
}
