package org.rmartini.galaxy.repository;

import org.rmartini.galaxy.entity.Forecast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastRepository extends CrudRepository<Forecast, Long> {

    /**
     * Get the weather for an specific day
     * @param day
     * @return Forecast object
     */
    Forecast findByDay(int day);

    /**
     * Get the list of forecasts given a kind of weather
     * @param weather
     * @return List<Forecast> objects
     */
    List<Forecast> findByWeather(String weather);

    /**
     * Get the list of forecasts given its perimeter
     * @param perimeter
     * @return
     */
    List<Forecast> findByPerimeter(double perimeter);

    /**
     * Get the first forecast ordered by its perimeter in a descending way
     * @return Forecast object
     */
    Forecast findFirstByOrderByPerimeterDesc();

}
