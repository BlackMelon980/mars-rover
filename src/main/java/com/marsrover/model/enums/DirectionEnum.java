package com.marsrover.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum DirectionEnum {

    N ("N"),
    E ("E"),
    S ("S"),
    W ("W");

    @Getter
    private final String value;
}
