package org.rmartini.galaxy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rmartini.galaxy.entity.Forecast;
import org.rmartini.galaxy.repository.ForecastRepository;
import org.rmartini.galaxy.util.WeatherKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ForecastRepositoryIntegrationTest {

    @Autowired
    private ForecastRepository forecastRepository;

    @Before
    public void initGalaxy() {
        this.forecastRepository.deleteAll();
        this.forecastRepository.save(new Forecast(360, WeatherKind.DROUGHT.getKind(), 0));
        this.forecastRepository.save(new Forecast(25, WeatherKind.RAIN.getKind(), 10));
        this.forecastRepository.save(new Forecast(70, WeatherKind.RAIN.getKind(), 20));
        this.forecastRepository.save(new Forecast(50, WeatherKind.UNKNOWN.getKind(), 0));
    }

    @Test
    public void testFindByDay() {
        Forecast weather = this.forecastRepository.findByDay(360);
        assertEquals(weather.getDay(), 360);
        assertEquals(weather.getWeather(), WeatherKind.DROUGHT.getKind());
    }

    @Test
    public void testFindByWeather() {
        List<Forecast> forecasts = this.forecastRepository.findByWeather(WeatherKind.RAIN.getKind());
        assertEquals(forecasts.size(), 2);
    }

    @Test
    public void testFindByPerimeter() {
        List<Forecast> forecasts = this.forecastRepository.findByPerimeter(10);
        assertEquals(forecasts.size(), 1);
        assertTrue(forecasts.get(0).getPerimeter() == 10);
    }

    @Test
    public void testFindFirstByOrderByPerimeterDesc() {
        Forecast forecast = this.forecastRepository.findFirstByOrderByPerimeterDesc();
        assertEquals(forecast.getWeather(), WeatherKind.RAIN.getKind());
        assertTrue(forecast.getPerimeter() == 20);
    }

}
