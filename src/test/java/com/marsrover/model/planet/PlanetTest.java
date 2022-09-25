package com.marsrover.model.planet;

import com.marsrover.model.obstacle.Obstacle;
import com.marsrover.model.position.Position;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PlanetTest {

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
    void shouldNotExistAnObstacleWithThisPosition() {

        Obstacle obstacle = planet.getObstacleByPosition(new Position(1, 1));
        assertNull(obstacle);

    }

    @Test
    void shouldExistAnObstacleWithThisPosition() {

        Obstacle obstacle = planet.getObstacleByPosition(new Position(3, 0));
        assertNotNull(obstacle);

    }

    @Test
    void getNewPositionAndIsNotOutOfBounds() {
        Position position = new Position(3, 1);
        Position newPosition = planet.updatePositionIfOutOfBounds(position);
        assertEquals(newPosition, position);
    }

    @Test
    void newPositionUpdateBecauseIsOutOfBounds() {
        Position position = new Position(5, 1);
        Position newPosition = planet.updatePositionIfOutOfBounds(position);
        assertEquals(newPosition, new Position(0, 1));
    }
}
