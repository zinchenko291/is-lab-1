package me.zinch.is.islab1jee8.exceptions;

public class ConstraintException extends BusinessException {
    public ConstraintException(String message) {
        super(message, 400);
    }
}
