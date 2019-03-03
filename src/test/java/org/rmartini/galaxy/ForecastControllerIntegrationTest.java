package org.rmartini.galaxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.rmartini.galaxy.controller.ForecastController;
import org.rmartini.galaxy.entity.Forecast;
import org.rmartini.galaxy.entity.PeakRain;
import org.rmartini.galaxy.repository.ForecastRepository;
import org.rmartini.galaxy.util.WeatherKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(ForecastController.class)
public class ForecastControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForecastRepository forecastRepository;

    @Test
    public void testGetWeatherByDay() throws Exception {

        int day = 360;

        when(forecastRepository.findByDay(day)).thenReturn(new Forecast(day, WeatherKind.DROUGHT.getKind(), 0));

        this.mockMvc.perform(get("/weather/{id}", day))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json("{ id: null, day: 360, weather: DROUGHT, perimeter: 0 }"));

    }

    @Test
    public void testGetForecastByWeather() throws Exception {

        when(forecastRepository.findByWeather(WeatherKind.SUNNY.getKind()))
                .thenReturn(Arrays.asList(new Forecast(25, WeatherKind.SUNNY.getKind(), 10),
                                          new Forecast(70, WeatherKind.SUNNY.getKind(), 20)));

        this.mockMvc.perform(get("/periods/{weather}", WeatherKind.SUNNY.getKind()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{ weather: SUNNY, quantity: 2 }"));

    }

}
