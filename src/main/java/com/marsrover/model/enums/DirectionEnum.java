package com.marsrover.model.enums;


public enum DirectionEnum {

    N("N"),
    E("E"),
    S("S"),
    W("W");


    private final String value;

    DirectionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
