package org.rmartini.galaxy.entity;

import lombok.Data;

@Data
public class Period {

    private String weather;
    private int quantity;

    public Period() { }

    public Period(String weather, int quantity) {
        this.weather = weather;
        this.quantity = quantity;
    }

}
