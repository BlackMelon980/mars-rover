package com.marsrover.model.space;

import com.marsrover.model.rover.Position;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class SpaceTest {

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
    void shouldNotExistAnObstacleWithThisPosition() {

        Obstacle obstacle = space.getObstacleByPosition(new Position(1, 1));
        assertNull(obstacle);

    }

    @Test
    void shouldExistAnObstacleWithThisPosition() {

        Obstacle obstacle = space.getObstacleByPosition(new Position(3, 0));
        assertNotNull(obstacle);

    }

    @Test
    void getNewPositionAndIsNotOutOfBounds() {
        Position position = new Position(3, 1);
        Position newPosition = space.updatePositionIfOutOfBounds(position);
        assertEquals(newPosition, position);
    }

    @Test
    void newPositionUpdateBecauseIsOutOfBounds() {
        Position position = new Position(5, 1);
        Position newPosition = space.updatePositionIfOutOfBounds(position);
        assertEquals(newPosition, new Position(0, 1));
    }
}
