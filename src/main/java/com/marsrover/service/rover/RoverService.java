package com.marsrover.service.rover;

import com.marsrover.model.enums.DirectionEnum;
import com.marsrover.model.rover.Position;
import com.marsrover.model.rover.Rover;
import com.marsrover.model.rover.RoverDto;
import com.marsrover.model.space.Space;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class RoverService {

    public Boolean create(RoverDto command, Space space) {

        if (!space.getObstacleByPositions(command.getPosition().getX(), command.getPosition().getY()).isEmpty()) {
            return false;
        }

        Rover rover = new Rover(new Position(command.getPosition().getX(), command.getPosition().getY()), command.getDirection());
        space.setRover(rover);
        return true;
    }

    public void moveRover(Space space, List<String> commands) {

        Map<DirectionEnum, List<DirectionEnum>> directionMap = getMapDirections();

        Rover rover = space.getRover();
        List<DirectionEnum> nextRoverDirections = directionMap.get(rover.getDirection());

        for (String command : commands) {
            switch (command.toUpperCase()) {
                case "R": {
                    rover.setDirection(DirectionEnum.valueOf(nextRoverDirections.get(1).getValue()));
                    break;
                }
                case "L": {
                    rover.setDirection(DirectionEnum.valueOf(nextRoverDirections.get(0).getValue()));
                    break;
                }
                case "F":
                case "B": {
                    move(space, command);
                    break;
                }
            }
        }
    }

    private Map<DirectionEnum, List<DirectionEnum>> getMapDirections() {
        Map<DirectionEnum, List<DirectionEnum>> directions = new HashMap<>();
        directions.put(DirectionEnum.N, new ArrayList<>(List.of(DirectionEnum.W, DirectionEnum.E)));
        directions.put(DirectionEnum.E, new ArrayList<>(List.of(DirectionEnum.N, DirectionEnum.S)));
        directions.put(DirectionEnum.S, new ArrayList<>(List.of(DirectionEnum.E, DirectionEnum.W)));
        directions.put(DirectionEnum.W, new ArrayList<>(List.of(DirectionEnum.S, DirectionEnum.N)));
        return directions;
    }

    private void move(Space space, String command) {

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
        //TODO: cosa fare se c'è un ostacolo?
        //TODO: se il rover arriva al limite dello spazio, riprendere dal lato opposto
        if (space.getObstacleByPositions(newPosition.getX(), newPosition.getY()).isEmpty()) {
            space.getRover().setPosition(newPosition);
        }
    }
}
