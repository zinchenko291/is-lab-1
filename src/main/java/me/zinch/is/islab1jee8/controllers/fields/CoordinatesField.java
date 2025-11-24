package me.zinch.is.islab1jee8.controllers.fields;

public enum CoordinatesField {
    ID("id"),
    X("x"),
    Y("y"),
    ;

    private final String value;

    CoordinatesField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
