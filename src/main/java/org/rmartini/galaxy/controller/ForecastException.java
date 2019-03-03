package org.rmartini.galaxy.controller;

public class ForecastException extends RuntimeException {

    public static String WEATHERKIND = "The weather kind is not allowed";
    public static String WEATHER = "The day must be between 0 and 3649";

    public ForecastException(String message) {
        super(message);
    }
}
