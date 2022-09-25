package com.marsrover.model.planet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marsrover.model.obstacle.Obstacle;
import com.marsrover.model.rover.Rover;

import java.util.List;


public class PlanetInfoResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    private Integer width;
    private Integer height;
    private List<Obstacle> obstacles;
    private Rover rover;

    public PlanetInfoResponse(Planet planet) {
        this.width = planet.getWidth();
        this.height = planet.getHeight();
        this.obstacles = planet.getObstacles();
        this.rover = planet.getRover();
    }

    public PlanetInfoResponse(String message, Planet planet) {
        this.message = message;
        this.width = planet.getWidth();
        this.height = planet.getHeight();
        this.obstacles = planet.getObstacles();
        this.rover = planet.getRover();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }
}
