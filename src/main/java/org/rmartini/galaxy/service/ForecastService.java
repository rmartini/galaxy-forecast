package org.rmartini.galaxy.service;

import java.io.Serializable;

public interface ForecastService extends Serializable {

    /**
     * Service that initializes the galaxy when the application is booting up
     */
    void initGalaxyForecast();

}
