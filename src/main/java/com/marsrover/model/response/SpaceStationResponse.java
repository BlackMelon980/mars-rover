package com.marsrover.model.response;

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

    private String message;
    private Rover rover;
    private List<Obstacle> obstacles;
    
}
