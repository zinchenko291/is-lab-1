package me.zinch.is.islab1jee8.models.fields;

public class CoordinatesField extends EnumField implements EntityField  {
    public CoordinatesField(String value) {
        super(value, v -> v.equals(value), new String[]{"id", "x", "y"});
    }

    @Override
    public boolean isStringType() {
        return false;
    }
}