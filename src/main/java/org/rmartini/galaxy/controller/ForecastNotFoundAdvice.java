package org.rmartini.galaxy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ForecastNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ForecastException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String forecastNotFoundHandler(ForecastException ex) {
        return ex.getMessage();
    }

}
