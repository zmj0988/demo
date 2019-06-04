package com.example.demo.consts;

public enum Events {
    APPROVE(1), REJECT(2);

    private Integer value;

    Events(int i) {
        this.value = i;
    }

    public Integer getValue() {
        return value;
    }
}