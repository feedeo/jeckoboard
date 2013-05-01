package com.feedeo.geckoboard.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Points {
    private List<Point> point;

    public List<Point> getPoint() {
        return point;
    }

    public Points setPoint(List<Point> point) {
        this.point = point;
        return this;
    }
}
