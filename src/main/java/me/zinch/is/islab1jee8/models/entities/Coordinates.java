package me.zinch.is.islab1jee8.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "coordinates")
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double x;

    @Column(nullable = false)
    @NotNull
    @Max(910)
    private Double y;

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

    public @NotNull Double getY() {
        return y;
    }

    public void setY(@NotNull Double y) {
        this.y = y;
    }
}
