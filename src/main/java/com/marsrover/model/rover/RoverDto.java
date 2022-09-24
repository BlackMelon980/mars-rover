package com.marsrover.model.rover;

import com.marsrover.model.enums.DirectionEnum;

public class RoverDto {

    private Position position;
    private DirectionEnum direction;


    public RoverDto() {
    }

    public RoverDto(Position position, DirectionEnum direction) {
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
