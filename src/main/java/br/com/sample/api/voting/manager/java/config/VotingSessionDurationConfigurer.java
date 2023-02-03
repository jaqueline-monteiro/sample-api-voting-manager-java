package br.com.sample.api.voting.manager.java.config;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Component;

import br.com.sample.api.voting.manager.java.model.VotingSession;

@Component
public class VotingSessionDurationConfigurer implements DurationConfigurer<Object> {
    
    public VotingSession applyDuration(Long duration) {
        OffsetDateTime startTime = OffsetDateTime.now();

        return VotingSession.builder()
                .startTime(startTime)
                .endTime(startTime.plusMinutes(duration))
                .build();
    }
}
