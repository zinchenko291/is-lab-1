package me.zinch.is.islab1jee8.models.fields;

public class VehicleField extends EnumField implements EntityField {
    public VehicleField(String value) {
        super(value, v -> v.equals(value), new String[]{"id", "name", "x", "y", "creationDate", "type", "enginePower",
                "numberOfWheels", "capacity", "distanceTravelled", "fuelConsumption", "fuelType"});
    }

    @Override
    public boolean isStringType() {
        return getValue().equals("name");
    }
}