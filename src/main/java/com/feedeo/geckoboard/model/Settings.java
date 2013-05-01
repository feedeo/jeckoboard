package com.feedeo.geckoboard.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Settings {
    private List<String> axisx;
    private List<String> axisy;
    private String colour;

    public Settings(List<String> axisx, List<String> axisy, String colour) {
        this.axisx = axisx;
        this.axisy = axisy;
        this.colour = colour;
    }

    public List<String> getAxisx() {
        return axisx;
    }

    public void setAxisx(List<String> axisx) {
        this.axisx = axisx;
    }

    public List<String> getAxisy() {
        return axisy;
    }

    public void setAxisy(List<String> axisy) {
        this.axisy = axisy;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
