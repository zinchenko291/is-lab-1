package me.zinch.is.islab1jee8.models.entities;

//@JsonbTypeAdapter(FuelTypeAdapter.class)
public enum FuelType {
    KEROSENE("KEROSENE"),
    ALCOHOL("ALCOHOL"),
    MANPOWER("MANPOWER"),
    PLASMA("PLASMA"),
    ANTIMATTER("ANTIMATTER")
    ;

    private final String value;

    FuelType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
