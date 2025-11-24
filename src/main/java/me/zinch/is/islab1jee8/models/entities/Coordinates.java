package me.zinch.is.islab1jee8.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "coordinates")
public class Coordinates {
    @Id
    private int id;

    @Column(nullable = false)
    private double x;

    @Column(nullable = false)
    @NotNull
    private Double y;

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public @NotNull Double getY() {
        return y;
    }

    public void setY(@NotNull Double y) {
        this.y = y;
    }
}
