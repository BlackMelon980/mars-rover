package com.marsrover.service.planet;

import com.marsrover.model.obstacle.Obstacle;
import com.marsrover.model.planet.Planet;
import com.marsrover.model.planet.PlanetDto;
import com.marsrover.model.position.Position;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PlanetServiceTest {

    @Inject
    PlanetService planetService;

    Planet planet;


    @BeforeEach
    void setUp() {
        planet = new Planet();
    }

    @Test
    void initPlanet() {

        PlanetDto planetDto = new PlanetDto(5, 7, 4);
        planetService.initPlanetWithObstacles(planet, planetDto);

        assertEquals(4, planet.getObstacles().size());
        assertEquals(5, planet.getWidth());
        assertEquals(7, planet.getHeight());
    }

    @Test
    void addObstacleAtPosition() {

        Position position = new Position(1, 1);
        Boolean obstacleWasAdded = planetService.addObstacle(planet, position);

        assertTrue(obstacleWasAdded);
        assertNotNull(planet.getObstacleByPosition(position));

    }

    @Test
    void cannotOverlapObstacles() {

        List<Obstacle> obstacles = new ArrayList<>(List.of(
                new Obstacle(new Position(1, 0)),
                new Obstacle(new Position(3, 0)),
                new Obstacle(new Position(3, 2)),
                new Obstacle(new Position(1, 3))
        ));
        planet.setObstacles(obstacles);

        Position position = new Position(1, 0);
        Boolean obstacleWasAdded = planetService.addObstacle(planet, position);

        assertFalse(obstacleWasAdded);

    }

    @Test
    void cannotAddObstacleOutsideOfPlanet() {

        Position position = new Position(10, 10);
        Boolean obstacleWasAdded = planetService.addObstacle(planet, position);

        assertFalse(obstacleWasAdded);
        assertNull(planet.getObstacleByPosition(position));

    }

    @Test
    void deleteObstacle() {

        List<Obstacle> obstacles = new ArrayList<>(List.of(
                new Obstacle(new Position(1, 0)),
                new Obstacle(new Position(3, 0))
        ));
        planet.setObstacles(obstacles);

        Position position = new Position(1, 0);

        assertNotNull(planet.getObstacleByPosition(position));
        Boolean obstacleWasDeleted = planetService.deleteObstacle(planet, position);

        assertTrue(obstacleWasDeleted);
        assertNull(planet.getObstacleByPosition(position));

    }

    @Test
    void cannotDeleteUnexistingObstacle() {

        List<Obstacle> obstacles = new ArrayList<>(List.of(
                new Obstacle(new Position(1, 0)),
                new Obstacle(new Position(3, 0))
        ));
        planet.setObstacles(obstacles);

        Position position = new Position(2, 1);
        assertNull(planet.getObstacleByPosition(position));
        Boolean obstacleWasDeleted = planetService.deleteObstacle(planet, position);

        assertFalse(obstacleWasDeleted);

    }

}
