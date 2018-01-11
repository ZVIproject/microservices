package com.zviproject.dbchangeservice.enums;

public enum QueueKey {
    QUEUE_NAME_POST("create"), QUEUE_NAME_PUT("update"), QUEUE_NAME_DELETE("delete");

    private String key;

    QueueKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
