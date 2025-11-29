package me.zinch.is.islab1jee8.models.dto.coordinates;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class CoordinatesWithoutIdDto {
    private double x;

    @NotNull
    @Max(910)
    private Double y;

    public CoordinatesWithoutIdDto() {
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
