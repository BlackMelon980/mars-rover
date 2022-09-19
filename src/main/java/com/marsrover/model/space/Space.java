package com.marsrover.model.space;

import com.marsrover.model.rover.Rover;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApplicationScoped
public class Space {

    private Integer width = 5;
    private Integer height = 5;
    private List<Obstacle> obstacles = new ArrayList<>();
    private Rover rover;

    public List<Obstacle> getObstacleByPositions(Integer positionX, Integer positionY) {
        Predicate<Obstacle> byPosition = obstacle -> obstacle.getPosition().getX().equals(positionX) && obstacle.getPosition().getY().equals(positionY);
        return obstacles.stream().filter(byPosition).collect(Collectors.toList());
    }

    public void clear() {
        obstacles.clear();
        rover = null;
    }

}
