package com.ningbai.blog.typeEnum;

public enum RecordType {
    LIKE(1),
    COLLECT(2),
    ;
    int type;

    public int getType() {
        return type;
    }

    RecordType(int type) {
        this.type = type;
    }
}
