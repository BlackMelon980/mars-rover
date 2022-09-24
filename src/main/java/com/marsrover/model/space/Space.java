package com.marsrover.model.space;

import com.marsrover.model.enums.DirectionEnum;
import com.marsrover.model.rover.Position;
import com.marsrover.model.rover.Rover;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@ApplicationScoped
public class Space {

    private Integer width = 5;
    private Integer height = 5;
    private List<Obstacle> obstacles = new ArrayList<>();
    private Rover rover = new Rover();


    public Obstacle getObstacleByPosition(Position position) {

        Predicate<Obstacle> byPosition = obstacle -> obstacle.getPosition().equals(position);
        List<Obstacle> obstacleList = obstacles.stream().filter(byPosition).collect(Collectors.toList());
        return obstacleList.isEmpty() ? null : obstacleList.get(0);

    }

    public Position updatePositionIfOutOfBounds(Position newPosition) {

        if (newPosition.getX() < 0) {
            newPosition.setX(width - 1);
        } else if (newPosition.getX() == width) {
            newPosition.setX(0);
        }

        if (newPosition.getY() < 0) {
            newPosition.setY(height - 1);
        } else if (newPosition.getY() == height) {
            newPosition.setY(0);
        }
        return newPosition;

    }

    public String getSpaceView() {

        StringBuilder spaceView = new StringBuilder();
        String[][] points = initSpaceView();
        spaceView.append("\n---------------------- \n");
        addObstaclesToView(points);
        addRoverToView(points);

        for (int y = 0; y < height; y++) {
            spaceView.append(" | ");
            for (int x = 0; x < width; x++) {
                spaceView.append(points[x][y]);
                spaceView.append("  ");
            }
            spaceView.append(" | \n");
        }
        return spaceView.toString();

    }

    private void addRoverToView(String[][] points) {

        Map<DirectionEnum, String> images = new HashMap<>() {{
            put(DirectionEnum.N, "▲");
            put(DirectionEnum.E, "▶︎");
            put(DirectionEnum.S, "▼");
            put(DirectionEnum.W, "◀︎");
        }};

        Position position = rover.getPosition();
        String roverImage = images.get(rover.getDirection());
        points[position.getX()][position.getY()] = roverImage;

    }

    private String[][] initSpaceView() {

        String[][] points = new String[width][height];
        for (int posY = 0; posY < height; posY++) {
            for (int posX = 0; posX < width; posX++) {
                points[posX][posY] = "◼︎";
            }
        }
        return points;

    }

    private void addObstaclesToView(String[][] points) {

        for (Obstacle obstacle : obstacles) {
            Position obstaclePosition = obstacle.getPosition();
            points[obstaclePosition.getX()][obstaclePosition.getY()] = "🪐";
        }

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
