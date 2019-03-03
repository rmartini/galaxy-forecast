package org.rmartini.galaxy.entity;

import lombok.Data;

import java.util.List;

@Data
public class PeakRain {

    private int quantity;
    private List<Integer> days;

    public PeakRain() { }

    public PeakRain(int quantity, List<Integer> days) {
        this.quantity = quantity;
        this.days = days;
    }

}
