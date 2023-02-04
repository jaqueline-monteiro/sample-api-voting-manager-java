package br.com.sample.api.voting.manager.java.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sample.api.voting.manager.java.config.DurationConfigurer;
import br.com.sample.api.voting.manager.java.exceptions.ResourceNotFoundException;
import br.com.sample.api.voting.manager.java.model.Schedule;
import br.com.sample.api.voting.manager.java.model.VotingSession;
import br.com.sample.api.voting.manager.java.repository.IVotingSessionRepository;
import br.com.sample.api.voting.manager.java.service.IScheduleService;
import br.com.sample.api.voting.manager.java.service.IVotingSessionService;

@Service
public class VotingSessionServiceImpl implements IVotingSessionService {

    @Autowired
    private IScheduleService scheduleService;
    
    @Autowired
    private DurationConfigurer<Object> durationConfigurer;

    @Autowired
    private IVotingSessionRepository votingSessionRepository;

    @Override
    public VotingSession openVotingSession(Long scheduleId, long duration) {
        Optional<Schedule> schedule = findScheduleById(scheduleId);

        VotingSession votingSession = applyVotingSessionDuration(duration);

        return votingSessionRepository.save(schedule.get().getId(), votingSession);
    }
    
    @Override
    public Optional<VotingSession> findById(Long id) {
        return votingSessionRepository.findById(id);
    }

    private Optional<Schedule> findScheduleById(Long scheduleId) {
        return Optional.of(scheduleService.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found")));
    }
    
    private VotingSession applyVotingSessionDuration(long duration) {
       return (VotingSession) durationConfigurer.applyDuration(duration);
    }


}
