package org.rmartini.galaxy.service;

import org.rmartini.galaxy.entity.Forecast;
import org.rmartini.galaxy.entity.Planet;
import org.rmartini.galaxy.entity.Point;
import org.rmartini.galaxy.repository.ForecastRepository;
import org.rmartini.galaxy.util.WeatherKind;

public class ForecastServiceImpl implements ForecastService {

    private ForecastRepository repository;

    private Planet ferengi;
    private Planet betasoide;
    private Planet vulcano;
    private Point sun = new Point(0,0);

    private final int cycle = 365;
    private final int years = 10;

    public ForecastServiceImpl(ForecastRepository repository, Planet ferengi, Planet betasoide, Planet vulcano) {
        this.repository = repository;
        this.ferengi = ferengi;
        this.betasoide = betasoide;
        this.vulcano = vulcano;

        this.repository.deleteAll();
        initGalaxyForecast();
    }

    @Override
    public void initGalaxyForecast() {

        for(int day = 0; day < (cycle * years); day++) {
            this.repository.save(getWeather(day));
        }
    }

    /**
     * Get the weather given a specific day following the established premises
     * @param day
     * @return Forecast object
     */
    private Forecast getWeather(int day) {
        Forecast weather;
        Point p1 = this.ferengi.getPosition(day);
        Point p2 = this.betasoide.getPosition(day);
        Point p3 = this.vulcano.getPosition(day);

        if (arePointsAligned(p1,p2,p3)) {
            // The planets are aligned
            if (isSunAlignedWithPoints(day)){
                //The Sun is aligned with the planets so the weather is Drought
                weather = new Forecast(day, WeatherKind.DROUGHT.getKind(), 0);
            } else {
                //The Sun is not aligned with the planets so the weather is Optimum
                weather = new Forecast(day, WeatherKind.OPTIMUM.getKind(), 0);
            }
        } else {
            // The planets are not aligned, they are forming a triangle instead
            if (isSunInsideTriangle(day)) {
                weather = new Forecast(day, WeatherKind.RAIN.getKind(), getPerimeter(p1, p2, p3));
            } else {
                //The Sun is not inside the triangle area so the weather is sunny
                weather = new Forecast(day, WeatherKind.SUNNY.getKind(), 0);
            }

        }

        return weather;
    }

    // Util Formulas

    /**
     * A function that determines if three points are aligned
     * @param p1
     * @param p2
     * @param p3
     * @return Boolean value
     */
    private boolean arePointsAligned(Point p1, Point p2, Point p3) {
        double result = p1.getX() * (p2.getY() - p3.getY()) +
                        p2.getX() * (p3.getY() - p1.getY()) +
                        p3.getX() * (p1.getY() - p2.getY());
        return (result == 0.0);
    }

    /**
     * A function that determines is any point is aligned with others
     * @param day
     * @return Boolean value
     */
    private boolean isSunAlignedWithPoints(int day){
        boolean r1 = arePointsAligned(this.sun, this.ferengi.getPosition(day), this.betasoide.getPosition(day));
        boolean r2 = arePointsAligned(this.sun, this.vulcano.getPosition(day), this.ferengi.getPosition(day));
        return (r1 == r2);
    }

    /**
     * A function that determines the perimeter given three points
     * @param p1
     * @param p2
     * @param p3
     * @return Double value
     */
    private double getPerimeter(Point p1, Point p2, Point p3) {
        // Get the distance between points
        double p12 = distanceBetweenPoints(p1, p2);
        double p23 = distanceBetweenPoints(p2, p3);
        double p31 = distanceBetweenPoints(p3, p1);

        return (p12 + p23 + p31);
    }

    /**
     * A function that determines the distance between two points
     * @param p1
     * @param p2
     * @return Double value
     */
    private double distanceBetweenPoints(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2));
    }

    /**
     * A function that determines is any point is inside the area of a triangle given a specific day
     * @param day
     * @return Boolean value
     */
    private boolean isSunInsideTriangle(int day) {
        // Get the orientation of each triangle
        boolean r1 = triangleOrientation(this.sun, this.ferengi.getPosition(day), this.betasoide.getPosition(day)) < 0.0;
        boolean r2 = triangleOrientation(this.sun, this.betasoide.getPosition(day), this.vulcano.getPosition(day)) < 0.0;
        boolean r3 = triangleOrientation(this.sun, this.vulcano.getPosition(day), this.ferengi.getPosition(day)) < 0.0;

        return ((r1 == r2) && (r2 == r3));
    }

    /**
     * A function that determines the orientation of a triangle given theirs points
     * @param p1
     * @param p2
     * @param p3
     * @return Double value
     */
    private double triangleOrientation(Point p1, Point p2, Point p3) {
        return ((p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY()));
    }

}
