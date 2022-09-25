package com.marsrover.service.rover;

import com.marsrover.model.enums.DirectionEnum;
import com.marsrover.model.obstacle.Obstacle;
import com.marsrover.model.planet.Planet;
import com.marsrover.model.position.Position;
import com.marsrover.model.rover.Rover;
import com.marsrover.model.rover.RoverDto;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class RoverServiceTest {

    @Inject
    RoverService roverService;
    Planet planet;
    List<Obstacle> obstacles;

    @BeforeEach
    void setUp() {

        planet = new Planet();
        obstacles = new ArrayList<>(List.of(
                new Obstacle(new Position(1, 0)),
                new Obstacle(new Position(3, 0)),
                new Obstacle(new Position(3, 2)),
                new Obstacle(new Position(1, 3))
        ));
        planet.setObstacles(obstacles);

    }

    @Test
    void updateRover() {

        RoverDto roverDto = new RoverDto();
        roverDto.setPosition(new Position(1, 1));
        roverDto.setDirection(DirectionEnum.N);

        assertTrue(roverService.updateRover(roverDto, planet));

        Rover rover = planet.getRover();
        assertEquals(new Position(1, 1), rover.getPosition());
        assertEquals(DirectionEnum.N, rover.getDirection());
    }

    @Test
    void cannotSetRoverPositionOutOfBounds() {

        RoverDto roverDto = new RoverDto();
        roverDto.setPosition(new Position(10, 10));
        roverDto.setDirection(DirectionEnum.N);

        assertFalse(roverService.updateRover(roverDto, planet));

        Rover rover = planet.getRover();
        assertEquals(new Position(0, 0), rover.getPosition());
        assertEquals(DirectionEnum.N, rover.getDirection());
    }

    @Test
    void roverCannotOverlapObstacle() {

        RoverDto roverDto = new RoverDto();
        roverDto.setPosition(new Position(3, 2));
        roverDto.setDirection(DirectionEnum.N);

        assertFalse(roverService.updateRover(roverDto, planet));

    }

    @Test
    void moveRoverWithAllCommands() {

        List<String> commands = new ArrayList<>(List.of("L", "L", "F"));

        roverService.moveRover(planet, commands);
        Rover rover = planet.getRover();

        assertEquals(new Position(0, 1), rover.getPosition());
        assertEquals(DirectionEnum.S, rover.getDirection());
    }

    @Test
    void moveRoverButIsInterruptedByObstacle() {

        List<String> commands = new ArrayList<>(List.of("R", "F"));
        Rover rover = planet.getRover();

        assertNotNull(roverService.moveRover(planet, commands));
        assertEquals(new Position(0, 0), rover.getPosition());
    }

    @Test
    void roverCannotMoveWithWrongCommands() {

        List<String> commands = new ArrayList<>(List.of("FR", "LB", "1"));

        roverService.moveRover(planet, commands);
        Rover rover = planet.getRover();

        assertEquals(new Position(0, 0), rover.getPosition());
        assertEquals(DirectionEnum.N, rover.getDirection());
    }

}
