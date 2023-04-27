package com.event.core.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> entityClass, long id) {
        super(String.format("FAILED to find %s with id: %d", entityClass.getSimpleName(), id));
    }

}