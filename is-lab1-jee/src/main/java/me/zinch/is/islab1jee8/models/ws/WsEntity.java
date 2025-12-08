package me.zinch.is.islab1jee8.models.ws;

public enum WsEntity {
    VEHICLE("VEHICLE"),
    COORDINATES("COORDINATES")
    ;

    private final String value;

    private WsEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
