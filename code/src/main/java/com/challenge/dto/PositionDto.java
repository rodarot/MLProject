package com.challenge.dto;

import com.challenge.util.geometry.Vector;

public class PositionDto {

    private float x;
    private float y;


    public PositionDto(Vector vector2) {
        this.x = Float.parseFloat(Double.toString(vector2.x));
        this.y = Float.parseFloat(Double.toString(vector2.y));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
