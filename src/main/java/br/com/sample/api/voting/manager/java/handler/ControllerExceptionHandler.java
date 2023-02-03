package br.com.sample.api.voting.manager.java.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.com.sample.api.voting.manager.java.exceptions.ResourceNotFoundException;
import br.com.sample.api.voting.manager.java.model.ErrorDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDTO> handle(HttpServletRequest request, NoHandlerFoundException exception) {
        log.error("Not found Error", exception);
        
        ErrorDTO apiError = ErrorDTO.builder()
                .error("Not found Error")
                .message(String.format("Route %s not found", request.getRequestURI()))
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDTO> handle(HttpServletRequest request, ResponseStatusException exception) {
        log.error("Response Status Error", exception);

        ErrorDTO apiError = ErrorDTO.builder()
                .error("Response Status Error")
                .message(exception.getMessage())
                .status(exception.getStatus().value())
                .build();

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDTO> handle(Exception exception) {
        log.error("Internal Server Error", exception);

        ErrorDTO apiError = ErrorDTO.builder()
                .error("Internal Server Error")
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handle(MethodArgumentNotValidException exception) {
        log.error("Validation Error", exception);
        
        ErrorDTO apiError = ErrorDTO.builder()
                .error("Validation Error")
                .message(exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(ResourceNotFoundException exception) {
        log.error("Resource Not Found", exception);
        
        ErrorDTO apiError = ErrorDTO.builder()
                .error("Resource Not Found")
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
