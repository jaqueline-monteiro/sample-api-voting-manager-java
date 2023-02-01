package br.com.sample.api.voting.manager.java.handler;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sample.api.voting.manager.java.exceptions.ResourceNotFoundException;
import br.com.sample.api.voting.manager.java.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralErrors(Exception ex) {
        log.error("Unhandled exception", ex);
        ErrorResponse error = new ErrorResponse("Internal Server Error", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        ErrorResponse error = new ErrorResponse("Validation Error", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("Resource Not Found", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
