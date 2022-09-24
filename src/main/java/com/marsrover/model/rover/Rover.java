package com.marsrover.model.rover;

import com.marsrover.model.enums.DirectionEnum;


public class Rover {

    private Position position = new Position(0, 0);
    private DirectionEnum direction = DirectionEnum.N;


    public Rover() {
    }

    public Rover(Position position, DirectionEnum direction) {
        this.position = position;
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }
}
