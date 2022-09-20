package com.marsrover.service.space;

import com.marsrover.model.rover.Position;
import com.marsrover.model.space.Obstacle;
import com.marsrover.model.space.Space;
import com.marsrover.model.space.SpaceDto;
import com.marsrover.utils.NumberUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class SpaceService {

    public void initSpaceWithObstacles(Space space, SpaceDto spaceDto) {

        space.setWidth(spaceDto.getWidth());
        space.setHeight(spaceDto.getHeight());
        space.setObstacles(new ArrayList<>());
        space.getRover().init();

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

    public void clearSpace(Space space) {
        space.clear();
    }

}
