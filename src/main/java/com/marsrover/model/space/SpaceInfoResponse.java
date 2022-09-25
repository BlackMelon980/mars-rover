package com.marsrover.model.space;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marsrover.model.obstacle.Obstacle;
import com.marsrover.model.rover.Rover;

import java.util.List;


public class SpaceInfoResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    private Integer width;
    private Integer height;
    private List<Obstacle> obstacles;
    private Rover rover;

    public SpaceInfoResponse(Space space) {
        this.width = space.getWidth();
        this.height = space.getHeight();
        this.obstacles = space.getObstacles();
        this.rover = space.getRover();
    }

    public SpaceInfoResponse(String message, Space space) {
        this.message = message;
        this.width = space.getWidth();
        this.height = space.getHeight();
        this.obstacles = space.getObstacles();
        this.rover = space.getRover();
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
