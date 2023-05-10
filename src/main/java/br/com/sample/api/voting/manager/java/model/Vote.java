package br.com.sample.api.voting.manager.java.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "Vote", tags = "Vote")
public class Vote implements Serializable {
    private static final long serialVersionUID = 612298191181160495L;

    @NotBlank(message = "Field CPF must not be null or blank.")
    @ApiModelProperty(value = "CPF of the member", required = true, example = "999.999.999-99")
    private String cpf;

    @NotBlank(message = "Field VOTE must not be null or blank and must be 'Sim' or 'Não'.")
    @Pattern(regexp = "^(Sim|Não)$", message = "VOTE must be 'Sim' ou 'Não'")
    @ApiModelProperty(value = "VOTE of the member", required = true, example = "Sim/Não")
    private String memberVote;
}
