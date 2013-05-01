package com.feedeo.geckoboard.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Point {
    private String ip = "";
    private int size;

    public String getIp() {
        return ip;
    }

    public Point setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Point setSize(int size) {
        this.size = size;
        return this;
    }
}
