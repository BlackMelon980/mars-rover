package com.marsrover.model.obstacle;

import com.marsrover.model.position.Position;


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
