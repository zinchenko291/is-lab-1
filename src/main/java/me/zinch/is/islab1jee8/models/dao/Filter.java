package me.zinch.is.islab1jee8.models.dao;

public class Filter<T> {
    private T field;
    private String value;

    public Filter() {
    }

    public T getField() {
        return field;
    }

    public void setField(T field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
