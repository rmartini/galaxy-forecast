package org.rmartini.galaxy.entity;

public class Planet {

    private int radius;
    private int velocity;
    private int direction;
    private final int cycle = 360;

    public Planet(int radius, int velocity, int direction) {
        this.radius = radius;
        this.velocity = velocity;
        this.direction = direction;
    }

    public Point getPosition(int day) {
        double gradian = Math.toRadians((this.velocity * this.direction * day) % cycle);
        double coordX = this.radius * Math.cos(gradian);
        double coordY = this.radius * Math.sin(gradian);
        return new Point(coordX, coordY);
    }
}
