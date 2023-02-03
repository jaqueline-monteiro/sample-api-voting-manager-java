package br.com.sample.api.voting.manager.java.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@EqualsAndHashCode
@ToString
public class ErrorDTO {

    private final String error;
    private final String message;
    private final Integer status;

    @Jacksonized
    @Builder
    private ErrorDTO(
            @NonNull String error,
            @NonNull String message,
            @NonNull Integer status) {

        this.error = error;
        this.message = message;
        this.status = status;
    }
}
