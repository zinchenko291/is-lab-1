package me.zinch.is.islab1jee8.models.fields;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class EnumField {
    private final String value;

    protected EnumField(String input, Predicate<? super String> predicate, String[] allowedValues) {
        Optional<String> variant = Arrays.stream(allowedValues)
                .filter(predicate)
                .findFirst();
        if (variant.isPresent()) {
            this.value = input;
        } else {
            throw new IllegalArgumentException("Invalid value: " + input);
        }
    }

    public String getValue() {
        return value;
    }
}
