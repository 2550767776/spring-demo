package com.example.entity;

import java.io.Serializable;

/**
 * 队列消息实体
 */
public class MqInfo implements Serializable {

    private static final long serialVersionUID = -1309228594406128394L;

    // 消息
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
