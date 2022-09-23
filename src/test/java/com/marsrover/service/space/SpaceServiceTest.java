package com.marsrover.service.space;

import com.marsrover.model.space.Space;
import com.marsrover.model.space.SpaceDto;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        SpaceDto spaceDto = new SpaceDto(5, 5, 4);
        spaceService.initSpaceWithObstacles(space, spaceDto);

        assertEquals(4, space.getObstacles().size());
    }
}
