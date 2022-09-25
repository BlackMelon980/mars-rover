package com.marsrover.service.space;

import com.marsrover.model.obstacle.Obstacle;
import com.marsrover.model.position.Position;
import com.marsrover.model.space.Space;
import com.marsrover.model.space.SpaceDto;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class SpaceServiceTest {

    @Inject
    SpaceService spaceService;

    Space space;


    @BeforeEach
    void setUp() {
        space = new Space();
    }

    @Test
    void initSpace() {

        SpaceDto spaceDto = new SpaceDto(5, 7, 4);
        spaceService.initSpaceWithObstacles(space, spaceDto);

        assertEquals(4, space.getObstacles().size());
        assertEquals(5, space.getWidth());
        assertEquals(7, space.getHeight());
    }

    @Test
    void addObstacleAtPosition() {

        Position position = new Position(1, 1);
        Boolean obstacleWasAdded = spaceService.addObstacle(space, position);

        assertTrue(obstacleWasAdded);
        assertNotNull(space.getObstacleByPosition(position));

    }

    @Test
    void cannotOverlapObstacles() {

        List<Obstacle> obstacles = new ArrayList<>(List.of(
                new Obstacle(new Position(1, 0)),
                new Obstacle(new Position(3, 0)),
                new Obstacle(new Position(3, 2)),
                new Obstacle(new Position(1, 3))
        ));
        space.setObstacles(obstacles);

        Position position = new Position(1, 0);
        Boolean obstacleWasAdded = spaceService.addObstacle(space, position);

        assertFalse(obstacleWasAdded);

    }

    @Test
    void cannotAddObstacleOutsideOfSpace() {

        Position position = new Position(10, 10);
        Boolean obstacleWasAdded = spaceService.addObstacle(space, position);

        assertFalse(obstacleWasAdded);
        assertNull(space.getObstacleByPosition(position));

    }

    @Test
    void deleteObstacle() {

        List<Obstacle> obstacles = new ArrayList<>(List.of(
                new Obstacle(new Position(1, 0)),
                new Obstacle(new Position(3, 0))
        ));
        space.setObstacles(obstacles);

        Position position = new Position(1, 0);

        assertNotNull(space.getObstacleByPosition(position));
        Boolean obstacleWasDeleted = spaceService.deleteObstacle(space, position);

        assertTrue(obstacleWasDeleted);
        assertNull(space.getObstacleByPosition(position));

    }

    @Test
    void cannotDeleteUnexistingObstacle() {

        List<Obstacle> obstacles = new ArrayList<>(List.of(
                new Obstacle(new Position(1, 0)),
                new Obstacle(new Position(3, 0))
        ));
        space.setObstacles(obstacles);

        Position position = new Position(2, 1);
        assertNull(space.getObstacleByPosition(position));
        Boolean obstacleWasDeleted = spaceService.deleteObstacle(space, position);

        assertFalse(obstacleWasDeleted);

    }

}
