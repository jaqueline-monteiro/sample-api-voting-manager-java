package br.com.sample.api.voting.manager.java.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@EqualsAndHashCode
@ToString
@Api(value = "ErrorDTO", tags = "ErrorDTO")
public class ErrorDTO {

    @ApiModelProperty(value = "Error", required = true, example = "Internal Server Error")
    private final String error;
    
    @ApiModelProperty(value = "Message", required = true, example = "Failed saving schedule with id: 12345678")
    private final String message;
    
    @ApiModelProperty(value = "Status", required = true, example = "500")
    private final Integer status;

    @Jacksonized
    @Builder
    private ErrorDTO(
            @NonNull String error,
            String message,
            @NonNull Integer status) {

        this.error = error;
        this.message = message;
        this.status = status;
    }
}
