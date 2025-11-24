package me.zinch.is.islab1jee8.models.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class CoordinatesId implements Serializable {
    private double x;

    @NotNull
    @Max(910)
    private Double y;

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
