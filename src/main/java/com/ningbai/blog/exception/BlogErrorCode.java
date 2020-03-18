package com.ningbai.blog.exception;

public enum BlogErrorCode implements ErrorCode {
    BLOG_NOT_FOUND("这篇博客不翼而飞了，回主页看看别的吧！");
    @Override
    public String getMessage(){
        return message;
    }
    private String message;

    BlogErrorCode(String message) {
        this.message = message;
    }
}
