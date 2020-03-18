package com.ningbai.blog.exception;

public class MyException extends RuntimeException {
    private String message;

    public MyException(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
