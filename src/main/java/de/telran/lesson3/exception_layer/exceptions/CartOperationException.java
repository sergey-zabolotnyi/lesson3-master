package de.telran.lesson3.exception_layer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CartOperationException extends RuntimeException {
    public CartOperationException(String message) {
        super(message);
    }
}
