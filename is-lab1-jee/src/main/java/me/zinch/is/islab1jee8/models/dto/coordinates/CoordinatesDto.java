package me.zinch.is.islab1jee8.models.dto.coordinates;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class CoordinatesDto {
    private int id;

    private double x;

    @NotNull
    @Max(910)
    private Double y;

    public CoordinatesDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public @NotNull @Max(910) Double getY() {
        return y;
    }

    public void setY(@NotNull @Max(910) Double y) {
        this.y = y;
    }
}
