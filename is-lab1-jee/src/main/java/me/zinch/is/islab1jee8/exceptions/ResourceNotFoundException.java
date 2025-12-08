package me.zinch.is.islab1jee8.exceptions;

import jakarta.ws.rs.core.Response;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String message) {
        super(message, Response.Status.NOT_FOUND.getStatusCode());
    }
}
