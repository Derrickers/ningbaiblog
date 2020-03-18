package com.ningbai.blog.exception;

public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUNT("这个用户好像消失了，去看看别人吧！");

    private String message;
    @Override
    public String getMessage() {
        return message;
    }

    UserErrorCode(String message) {
        this.message = message;
    }
}
