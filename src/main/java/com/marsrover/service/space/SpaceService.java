package com.marsrover.service.space;

import com.marsrover.model.rover.Position;
import com.marsrover.model.space.Obstacle;
import com.marsrover.model.space.Space;
import com.marsrover.model.space.SpaceDto;
import com.marsrover.utils.NumberUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SpaceService {

    public void initSpaceWithObstacles(Space space, SpaceDto spaceDto) {

        space.setWidth(spaceDto.getWidth());
        space.setHeight(spaceDto.getHeight());
        space.setObstacles(new ArrayList<>());

        List<Obstacle> obstacles = new ArrayList<>();
        int obstacleIndex = spaceDto.getObstaclesCount();

        while (obstacleIndex > 0) {

            int positionX = NumberUtils.getRandomNumber(0, space.getWidth() - 1);
            int positionY = NumberUtils.getRandomNumber(0, space.getHeight() - 1);

            List<Obstacle> existingObstacle = space.getObstacleByPositions(positionX, positionY);

            if (existingObstacle.isEmpty()) {
                obstacles.add(new Obstacle(new Position(positionX, positionY)));
                obstacleIndex--;
            }
        }
        space.setObstacles(obstacles);
    }

    public void clearSpace(Space space) {
        space.clear();
    }

    public String getSpaceView(Space space) {
        String spaceView = "";
        Integer[][] points = initSpaceView(space.getWidth(), space.getHeight());

        addObstaclesToView(space, points);

        if (space.getRover() != null) {
            Position roverPosition = space.getRover().getPosition();
            points[roverPosition.getX()][roverPosition.getY()] = 2;
        }

        for (int posY = 0; posY < space.getHeight(); posY++) {

            spaceView += "|";
            for (int posX = 0; posX < space.getWidth(); posX++) {
                spaceView += points[posX][posY] + "  ";
            }
            spaceView += "|\n";
        }

        return spaceView;
    }

    private Integer[][] initSpaceView(Integer width, Integer height) {
        Integer[][] points = new Integer[width][height];
        for (int posY = 0; posY < height; posY++) {
            for (int posX = 0; posX < width; posX++) {
                points[posX][posY] = 0;
            }
        }
        return points;
    }

    private void addObstaclesToView(Space space, Integer[][] points) {
        for (Obstacle obstacle : space.getObstacles()) {
            Position obstaclePosition = obstacle.getPosition();

            points[obstaclePosition.getX()][obstaclePosition.getY()] = 1;
        }
    }
}
