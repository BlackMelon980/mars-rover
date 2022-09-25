package com.marsrover.model.planet;

public class PlanetDto {

    private Integer width;
    private Integer height;
    private Integer obstaclesCount;


    public PlanetDto(Integer width, Integer height, Integer obstaclesCount) {
        this.width = width;
        this.height = height;
        this.obstaclesCount = obstaclesCount;
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

    public Integer getObstaclesCount() {
        return obstaclesCount;
    }

    public void setObstaclesCount(Integer obstaclesCount) {
        this.obstaclesCount = obstaclesCount;
    }
}
