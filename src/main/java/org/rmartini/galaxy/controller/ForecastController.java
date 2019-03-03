package org.rmartini.galaxy.controller;

import jdk.nashorn.internal.parser.JSONParser;
import org.rmartini.galaxy.entity.Forecast;
import org.rmartini.galaxy.entity.PeakRain;
import org.rmartini.galaxy.entity.Period;
import org.rmartini.galaxy.repository.ForecastRepository;
import org.rmartini.galaxy.util.WeatherKind;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ForecastController {

    private final ForecastRepository repository;

    public ForecastController(ForecastRepository repository) {
        this.repository = repository;
    }

    /**
     * Show as string how many endpoints are available in the application
     * @return String
     */
    @GetMapping("/")
    String initial() {
        return "<strong>Endpoints available:</strong><br/>" +
                "- https://galaxy-forecast-rmartini.herokuapp.com/weather/{day}<br/>" +
                "&nbsp; &nbsp; &nbsp; - <i>Param {day} must be between 0 and 3650</i> <br/><br/>" +
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
        if(day < 0 || day > 3650 ){
            throw new ForecastException(ForecastException.WEATHER);
        }
        return repository.findByDay(day);
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
        List<Forecast> forecasts = repository.findByWeather(kind.toUpperCase());
        return new Period(kind.toUpperCase(), forecasts.size());
    }

    /**
     * Resource that return the forecast with the quantity of it and which days will occur when the weather will have its peaks of rain
     * @return PeakRain object
     */
    @GetMapping("/peakrain")
    PeakRain getPeakRain() {
        Forecast forecast = repository.findFirstByOrderByPerimeterDesc();
        List<Forecast> peaks = repository.findByPerimeter(forecast.getPerimeter());

        List<Integer> days = new ArrayList<>();

        for (Forecast weather : peaks) {
            days.add(weather.getDay());
        }

        return new PeakRain(peaks.size(), days);
    }

}
