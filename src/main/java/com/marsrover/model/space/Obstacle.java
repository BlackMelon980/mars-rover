package com.marsrover.model.space;

import com.marsrover.model.rover.Position;


public class Obstacle {

    private Position position;

    public Obstacle(Position newPosition) {
        position = newPosition;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
