package com.marsrover.service.rover;

import com.marsrover.model.enums.DirectionEnum;
import com.marsrover.model.rover.Position;
import com.marsrover.model.rover.Rover;
import com.marsrover.model.rover.RoverDto;
import com.marsrover.model.space.Obstacle;
import com.marsrover.model.space.Space;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class RoverService {

    final Map<DirectionEnum, List<DirectionEnum>> directionMap = getMapDirections();


    public Boolean changeValues(RoverDto command, Space space) {

        if (space.getObstacleByPosition(command.getPosition()) != null) {
            return false;
        }

        space.setRoverValues(command.getPosition(), command.getDirection());
        return true;
    }

    public Obstacle moveRover(Space space, List<String> commands) {

        Rover rover = space.getRover();
        Obstacle obstacle = null;

        for (String command : commands) {
            switch (command.toUpperCase()) {
                case "R":
                case "L": {
                    rover.setDirection(getNextDirection(rover.getDirection(), command));
                    break;
                }
                case "F":
                case "B": {
                    obstacle = move(space, command);
                    break;
                }
            }
            if (obstacle != null) {
                return obstacle;
            }
        }
        return null;
    }

    private Map<DirectionEnum, List<DirectionEnum>> getMapDirections() {

        Map<DirectionEnum, List<DirectionEnum>> directions = new HashMap<>();
        directions.put(DirectionEnum.N, new ArrayList<>(List.of(DirectionEnum.W, DirectionEnum.E)));
        directions.put(DirectionEnum.E, new ArrayList<>(List.of(DirectionEnum.N, DirectionEnum.S)));
        directions.put(DirectionEnum.S, new ArrayList<>(List.of(DirectionEnum.E, DirectionEnum.W)));
        directions.put(DirectionEnum.W, new ArrayList<>(List.of(DirectionEnum.S, DirectionEnum.N)));
        return directions;

    }

    private DirectionEnum getNextDirection(DirectionEnum currentDirection, String command) {

        List<DirectionEnum> nextRoverDirections = directionMap.get(currentDirection);
        return command.equals("R") ? DirectionEnum.valueOf(nextRoverDirections.get(1).getValue()) : DirectionEnum.valueOf(nextRoverDirections.get(0).getValue());
    }

    private Obstacle move(Space space, String command) {

        Position roverPosition = space.getRover().getPosition();
        Position newPosition = new Position(roverPosition.getX(), roverPosition.getY());

        switch (space.getRover().getDirection()) {
            case N: {
                if (command.equals("F")) {
                    newPosition.setY(roverPosition.getY() - 1);
                } else {
                    newPosition.setY(roverPosition.getY() + 1);
                }
                break;
            }
            case E: {
                if (command.equals("F")) {
                    newPosition.setX(roverPosition.getX() + 1);
                } else {
                    newPosition.setX(roverPosition.getX() - 1);
                }
                break;
            }
            case S: {
                if (command.equals("F")) {
                    newPosition.setY(roverPosition.getY() + 1);
                } else {
                    newPosition.setY(roverPosition.getY() - 1);
                }
                break;
            }
            case W: {
                if (command.equals("F")) {
                    newPosition.setX(roverPosition.getX() - 1);
                } else {
                    newPosition.setX(roverPosition.getX() + 1);
                }
                break;
            }
        }
        newPosition = space.updatePositionIfOutOfBounds(newPosition);

        Obstacle existingObstacle = space.getObstacleByPosition(newPosition);
        if (existingObstacle == null) {
            space.getRover().setPosition(newPosition);
        }
        return existingObstacle;
    }
}
