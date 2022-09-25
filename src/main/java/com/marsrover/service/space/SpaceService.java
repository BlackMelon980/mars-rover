package com.marsrover.service.space;

import com.marsrover.model.obstacle.Obstacle;
import com.marsrover.model.position.Position;
import com.marsrover.model.rover.Rover;
import com.marsrover.model.space.Space;
import com.marsrover.model.space.SpaceDto;
import com.marsrover.utils.NumberUtils;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpaceService {

    public void initSpaceWithObstacles(Space space, SpaceDto spaceDto) {

        space.setWidth(spaceDto.getWidth());
        space.setHeight(spaceDto.getHeight());

        int obstacleIndex = spaceDto.getObstaclesCount();

        while (obstacleIndex > 0) {

            Position newPosition = new Position(
                    NumberUtils.getRandomNumber(0, space.getWidth() - 1),
                    NumberUtils.getRandomNumber(0, space.getHeight() - 1)
            );

            Obstacle existingObstacle = space.getObstacleByPosition(newPosition);
            Position roverPosition = space.getRover().getPosition();

            if (!newPosition.equals(roverPosition) && existingObstacle == null) {
                space.getObstacles().add(new Obstacle(newPosition));
                obstacleIndex--;
            }
        }

    }

    public Boolean addObstacle(Space space, Position position) {

        Boolean isOutOfBounds = space.checkPositionOutOfBounds(position);
        if (isOutOfBounds) {
            return false;
        }
        Obstacle obstacle = space.getObstacleByPosition(position);
        Rover rover = space.getRover();
        if (obstacle != null || position.equals(rover.getPosition())) {
            return false;
        }
        space.addObstacleAtPosition(position);
        return true;

    }

    public Boolean deleteObstacle(Space space, Position position) {

        Obstacle obstacle = space.getObstacleByPosition(position);
        if (obstacle == null) {
            return false;
        }

        space.deleteObstacle(position);
        return true;

    }
}
