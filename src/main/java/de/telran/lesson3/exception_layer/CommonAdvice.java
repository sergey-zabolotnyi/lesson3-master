package de.telran.lesson3.exception_layer;

import de.telran.lesson3.exception_layer.exceptions.EntityValidationException;
import de.telran.lesson3.exception_layer.exceptions.ThirdTestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonAdvice {

    @ExceptionHandler(ThirdTestException.class)
    public ResponseEntity<Response> handleException(ThirdTestException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityValidationException.class)
    public ResponseEntity<Response> handleException(EntityValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}