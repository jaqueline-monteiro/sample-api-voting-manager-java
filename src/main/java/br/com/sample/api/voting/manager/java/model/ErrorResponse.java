package br.com.sample.api.voting.manager.java.model;

import java.util.List;

public class ErrorResponse {

    private String message;
    private List<String> errors;

    public ErrorResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
