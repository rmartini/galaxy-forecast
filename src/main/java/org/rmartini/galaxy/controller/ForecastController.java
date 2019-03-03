package org.rmartini.galaxy.controller;

import org.rmartini.galaxy.entity.Forecast;
import org.rmartini.galaxy.entity.PeakRain;
import org.rmartini.galaxy.entity.Period;
import org.rmartini.galaxy.service.ForecastService;
import org.rmartini.galaxy.util.WeatherKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    /**
     * Show as string how many endpoints are available in the application
     * @return String
     */
    @GetMapping("/")
    String initial() {
        return "<strong>Endpoints available:</strong><br/>" +
                "- https://galaxy-forecast-rmartini.herokuapp.com/weather/{day}<br/>" +
                "&nbsp; &nbsp; &nbsp; - <i>Param {day} must be between 0 and 3649</i> <br/><br/>" +
                "- https://galaxy-forecast-rmartini.herokuapp.com/periods/{weather}<br/>" +
                "&nbsp; &nbsp; &nbsp; - <i>Param {weather} values allowed (DROUGHT, RAIN, SUNNY, OPTIMUM)</i> <br/><br/>" +
                "- https://galaxy-forecast-rmartini.herokuapp.com/peakrain";
    }

    /**
     * Resource that return the weather for a specific day
     * URL http://[domain]:[port]/weather/{day}
     * @param day
     * @return Forecast object
     * @throws ForecastException
     */
    @GetMapping("/weather/{day}")
    Forecast getWeatherDay(@PathVariable(value="day", required=true) int day) throws ForecastException {
        if(day < 0 || day >= 3650 ){
            throw new ForecastException(ForecastException.WEATHER);
        }
        return forecastService.getWeatherDay(day);
    }

    /**
     * Resource that return the period given the weather's kind (DROUGHT|RAIN|SUNNY|OPTIMUM) which contains the quantity of days
     * URL http://[domain]:[port]/periods/{weather}
     * @param kind
     * @return Period object
     * @throws ForecastException
     */
    @GetMapping("/periods/{weather}")
    Period getPeriodByWeather(@PathVariable(value="weather", required=true) String kind) throws ForecastException {
        if(kind.isEmpty() || !WeatherKind.contains(kind)){
            throw new ForecastException(ForecastException.WEATHERKIND);
        }
        return forecastService.getPeriodByWeather(kind);
    }

    /**
     * Resource that return the forecast with the quantity of it and which days will occur when the weather will have its peaks of rain
     * @return PeakRain object
     */
    @GetMapping("/peakrain")
    PeakRain getPeakRain() {
        return forecastService.getPeakRain();
    }

}
