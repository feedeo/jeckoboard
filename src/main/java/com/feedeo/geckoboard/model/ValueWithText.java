package com.feedeo.geckoboard.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValueWithText {
    private String text;
    private Number value;

    public ValueWithText(Number value, String text) {
        this.value = value;
        this.text = text;
    }

    public Number getValue() {
        return value;
    }

    public ValueWithText setValue(int value) {
        this.value = value;
        return this;
    }

    public String getText() {
        return text;
    }

    public ValueWithText setText(String text) {
        this.text = text;
        return this;
    }
}
