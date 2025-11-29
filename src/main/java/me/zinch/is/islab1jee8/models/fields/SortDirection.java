package me.zinch.is.islab1jee8.models.fields;

public class SortDirection extends EnumField {
    public SortDirection(String direction) {
        super(direction, v -> v.equalsIgnoreCase(direction), new String[]{"ASC", "DESC"});
    }
}