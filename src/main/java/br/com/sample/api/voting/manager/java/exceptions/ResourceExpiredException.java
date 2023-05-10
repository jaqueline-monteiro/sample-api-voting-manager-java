package br.com.sample.api.voting.manager.java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceExpiredException extends RuntimeException {
    private static final long serialVersionUID = 2503484031432193634L;
    
    public ResourceExpiredException(String message) {
        super(message);
    }

    public ResourceExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
