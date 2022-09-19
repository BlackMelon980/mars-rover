package com.marsrover.model.rover;

import com.marsrover.model.enums.DirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoverDto {

    private Position position;
    private DirectionEnum direction;

}
