package me.zinch.is.islab1jee8.controllers.fields;

public enum VehicleField {
    ID("id"),
    NAME("name"),
    X("x"),
    Y("y"),
    CREATION_DATE("creationDate"),
    TYPE("type"),
    ENGINE_POWER("enginePower"),
    NUMBER_OF_WHEELS("numberOfWheels"),
    CAPACITY("capacity"),
    DISTANCE_TRAVELLED("distanceTravelled"),
    FUEL_CONSUMPTION("fuelConsumption"),
    FUEL_TYPE("fuelType"),
    ;

    private final String value;

    VehicleField(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
