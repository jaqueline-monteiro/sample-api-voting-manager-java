package br.com.sample.api.voting.manager.java.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Api(value = "VotingSession", tags = "VotingSession")
public class VotingSession implements Serializable {
    private static final long serialVersionUID = 510537799219257095L;
    
    @ApiModelProperty(value = "Voting Session identification number", required = false, example = "1234567890", hidden = true)
    private Long id;

    @ApiModelProperty(value = "Voting Session start time", required = false, example = "2023-02-04T15:45:16.125+01:00", hidden = true)
    private OffsetDateTime startTime;

    @ApiModelProperty(value = "Voting Session end time", required = false, example = "2023-02-04T15:45:16.125+01:00", hidden = true)
    private OffsetDateTime endTime;
    
    @Builder
    public VotingSession(OffsetDateTime startTime, OffsetDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
