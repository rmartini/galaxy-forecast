package org.rmartini.galaxy.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int day;
    private String weather;
    private double perimeter;

    public Forecast() {}

    public Forecast(int day, String weather, double perimeter){
        this.day = day;
        this.weather = weather;
        this.perimeter = perimeter;
    }

    public int getDay() {
        return day;
    }

    public String getWeather() {
        return weather;
    }

    public double getPerimeter() {
        return perimeter;
    }
}
