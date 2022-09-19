package com.marsrover.model.space;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceDto {

    private Integer width;
    private Integer height;
    private Integer obstaclesCount;
}
