package br.com.sample.api.voting.manager.java.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    public VotingSession(Long id, OffsetDateTime startTime, OffsetDateTime endTime) {
    }

    public VotingSession updateWith(Integer duration) {
        if (duration == null) {
            duration = 1;
        }

        this.startTime = OffsetDateTime.now();
        this.endTime = this.startTime.plusMinutes(duration);

        return new VotingSession(
                this.id,
                this.startTime,
                this.endTime);
    }
}
