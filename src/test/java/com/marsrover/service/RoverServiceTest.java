package com.marsrover.service;

import com.marsrover.model.enums.DirectionEnum;
import com.marsrover.model.rover.Position;
import com.marsrover.model.rover.RoverDto;
import com.marsrover.model.space.Obstacle;
import com.marsrover.model.space.Space;
import com.marsrover.service.rover.RoverService;
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
    Space space;
    List<Obstacle> obstacles;

    @BeforeEach
    void setUp() {

        space = new Space();
        obstacles = new ArrayList<>(List.of(
                new Obstacle(new Position(1, 0)),
                new Obstacle(new Position(3, 0)),
                new Obstacle(new Position(3, 2)),
                new Obstacle(new Position(1, 3))
        ));
        space.setObstacles(obstacles);

    }

    @Test
    void createRover() {

        RoverDto roverDto = new RoverDto();
        roverDto.setPosition(new Position(1, 1));
        roverDto.setDirection(DirectionEnum.N);

        assertTrue(roverService.create(roverDto, space));

    }

    @Test
    void roverIsNotCreatedBecauseExistAnObstacle() {

        RoverDto roverDto = new RoverDto();
        roverDto.setPosition(new Position(3, 2));
        roverDto.setDirection(DirectionEnum.N);

        assertFalse(roverService.create(roverDto, space));

    }

    @Test
    void moveRoverWithAllCommands() {

        List<String> commands = new ArrayList<>(List.of("L", "L", "F"));

        assertNull(roverService.moveRover(space, commands));

    }

    @Test
    void moveRoverButIsInterruptedByObstacle() {

        List<String> commands = new ArrayList<>(List.of("R", "F"));

        assertNotNull(roverService.moveRover(space, commands));

    }
}
