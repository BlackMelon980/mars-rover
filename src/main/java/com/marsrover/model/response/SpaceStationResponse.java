package com.marsrover.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marsrover.model.rover.Rover;
import com.marsrover.model.space.Obstacle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceStationResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    private Rover rover;
    private List<Obstacle> obstacles;


    public SpaceStationResponse(Rover rover, List<Obstacle> obstacles) {
        this.rover = rover;
        this.obstacles = obstacles;
    }
}
