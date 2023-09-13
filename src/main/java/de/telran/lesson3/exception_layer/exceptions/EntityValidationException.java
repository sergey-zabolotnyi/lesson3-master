package de.telran.lesson3.exception_layer.exceptions;

public class EntityValidationException extends RuntimeException {

    public EntityValidationException(String message) {
        super(message);
    }
}