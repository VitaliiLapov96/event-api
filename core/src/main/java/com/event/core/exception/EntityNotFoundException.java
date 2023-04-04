package com.event.core.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class entity, long ticketId) {
        super(String.format("FAILED to find %s: %d", entity.getSimpleName(), ticketId));
    }

}