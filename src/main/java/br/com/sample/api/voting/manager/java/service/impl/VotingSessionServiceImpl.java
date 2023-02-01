package br.com.sample.api.voting.manager.java.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sample.api.voting.manager.java.exceptions.ResourceNotFoundException;
import br.com.sample.api.voting.manager.java.model.Schedule;
import br.com.sample.api.voting.manager.java.model.VotingSession;
import br.com.sample.api.voting.manager.java.repository.IScheduleRepository;
import br.com.sample.api.voting.manager.java.repository.IVotingSessionRepository;
import br.com.sample.api.voting.manager.java.service.IVotingSessionService;

@Service
public class VotingSessionServiceImpl implements IVotingSessionService {

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Autowired
    private IVotingSessionRepository votingSessionRepository;

    @Override
    public void openVotingSession(Long scheduleId, Integer duration) {
        VotingSession votingSession = new VotingSession();

        Optional<Schedule> schedule = Optional
                .of(scheduleRepository.findById(scheduleId)
                        .orElseThrow(() -> new ResourceNotFoundException("Schedule not found")));

        votingSession.updateWith(duration);

        votingSessionRepository.save(schedule.get().getId(), votingSession);
    }
}
