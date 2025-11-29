package me.zinch.is.islab1jee8.exceptions;

public abstract class BusinessException extends RuntimeException {
    BusinessException(String message) {
        super(message);
    }
}
