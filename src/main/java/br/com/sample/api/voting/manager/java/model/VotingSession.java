package br.com.sample.api.voting.manager.java.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Api(value = "VotingSession", tags = "VotingSession")
public class VotingSession implements Serializable {
    private static final long serialVersionUID = 510537799219257095L;

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(hidden = true)
    private OffsetDateTime startTime;

    @ApiModelProperty(hidden = true)
    private OffsetDateTime endTime;

    @ApiModelProperty(hidden = true)
    private Integer duration;
}
