package me.zinch.is.islab1jee8.models.dao.fields;

import java.util.Objects;

public class WheelRange {
    private Integer min;
    private Integer max;

    public WheelRange() {}

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelRange that = (WheelRange) o;
        return Objects.equals(min, that.min) && Objects.equals(max, that.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    @Override
    public String toString() {
        return "WheelRange{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
