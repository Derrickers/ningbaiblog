package com.ningbai.blog.typeEnum;

public enum StateType {
    READ(1),
    UNREAD(0);
    int type;

    public int getType() {
        return type;
    }

    StateType(int type) {
        this.type = type;
    }
}
