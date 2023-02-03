package br.com.sample.api.voting.manager.java.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "Schedule", tags = "Schedule")
public class Schedule implements Serializable {
    private static final long serialVersionUID = -7794951910698159678L;

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotBlank(message = "Field must not be null or blank.")
    @ApiModelProperty(value = "Title of the schedule", required = true, example = "Board meeting")
    private String title;

    @NotBlank(message = "Field must not be null or blank.")
    @ApiModelProperty(value = "Description of the schedule", required = true, example = "Discussion of budget")
    private String description;

    public Schedule() {
        
    }

    public Schedule(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
