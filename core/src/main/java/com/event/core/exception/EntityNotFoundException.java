package com.event.core.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final String FAILED_TO_FIND_ERROR_MSG = "FAILED to find %s";
    private static final String FAILED_TO_FIND_BY_ID_ERROR_MSG = "FAILED to find %s with id: %d";

    public EntityNotFoundException(Class<?> entityClass) {
        super(String.format(FAILED_TO_FIND_ERROR_MSG, entityClass.getSimpleName()));
    }

    public EntityNotFoundException(Class<?> entityClass, long id) {
        super(String.format(FAILED_TO_FIND_BY_ID_ERROR_MSG, entityClass.getSimpleName(), id));
    }

}