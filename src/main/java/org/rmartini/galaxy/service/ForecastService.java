package org.rmartini.galaxy.service;

import org.rmartini.galaxy.entity.Forecast;
import org.rmartini.galaxy.entity.PeakRain;
import org.rmartini.galaxy.entity.Period;

import java.io.Serializable;

public interface ForecastService extends Serializable {

    /**
     * Service that initializes the galaxy when the application is booting up
     */
    void initGalaxyForecast();

    /**
     * Get the weather for a specific day
     * @param day
     * @return Forecast object
     */
    Forecast getWeatherDay(int day);

    /**
     * Get the period given the weather's kind (DROUGHT|RAIN|OPTIMUM|UNKNOWN) which contains the quantity of days
     * @param weather
     * @return Period object
     */
    Period getPeriodByWeather(String weather);

    /**
     * Get the forecast with the quantity of it and which days will occur when the weather will have its peaks of rain
     * @return PeakRain object
     */
    PeakRain getPeakRain();

}
