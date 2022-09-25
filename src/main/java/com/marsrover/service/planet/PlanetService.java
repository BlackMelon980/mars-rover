package com.marsrover.service.planet;

import com.marsrover.model.obstacle.Obstacle;
import com.marsrover.model.planet.Planet;
import com.marsrover.model.planet.PlanetDto;
import com.marsrover.model.position.Position;
import com.marsrover.model.rover.Rover;
import com.marsrover.utils.NumberUtils;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlanetService {

    public void initPlanetWithObstacles(Planet planet, PlanetDto planetDto) {

        planet.setWidth(planetDto.getWidth());
        planet.setHeight(planetDto.getHeight());

        int obstacleIndex = planetDto.getObstaclesCount();

        while (obstacleIndex > 0) {

            Position newPosition = new Position(
                    NumberUtils.getRandomNumber(0, planet.getWidth() - 1),
                    NumberUtils.getRandomNumber(0, planet.getHeight() - 1)
            );

            Obstacle existingObstacle = planet.getObstacleByPosition(newPosition);
            Position roverPosition = planet.getRover().getPosition();

            if (!newPosition.equals(roverPosition) && existingObstacle == null) {
                planet.getObstacles().add(new Obstacle(newPosition));
                obstacleIndex--;
            }
        }

    }

    public Boolean addObstacle(Planet planet, Position position) {

        Boolean isOutOfBounds = planet.checkPositionOutOfBounds(position);
        if (isOutOfBounds) {
            return false;
        }
        Obstacle obstacle = planet.getObstacleByPosition(position);
        Rover rover = planet.getRover();
        if (obstacle != null || position.equals(rover.getPosition())) {
            return false;
        }
        planet.addObstacleAtPosition(position);
        return true;

    }

    public Boolean deleteObstacle(Planet planet, Position position) {

        Obstacle obstacle = planet.getObstacleByPosition(position);
        if (obstacle == null) {
            return false;
        }

        planet.deleteObstacle(position);
        return true;

    }
}
