package com.ningbai.blog.typeEnum;

public enum ReceiverType {
    BLOG(1),
    USER(2),
    COMMENT(3);
    int type;

    public int getType() {
        return type;
    }

    ReceiverType(int type) {
        this.type = type;
    }
}
