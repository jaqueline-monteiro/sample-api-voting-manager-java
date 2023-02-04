package br.com.sample.api.voting.manager.java.service.impl;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sample.api.voting.manager.java.exceptions.ResourceExpiredException;
import br.com.sample.api.voting.manager.java.exceptions.ResourceNotFoundException;
import br.com.sample.api.voting.manager.java.model.Schedule;
import br.com.sample.api.voting.manager.java.model.Vote;
import br.com.sample.api.voting.manager.java.model.VotingSession;
import br.com.sample.api.voting.manager.java.repository.IVoteRepository;
import br.com.sample.api.voting.manager.java.service.IScheduleService;
import br.com.sample.api.voting.manager.java.service.IVoteService;
import br.com.sample.api.voting.manager.java.service.IVotingSessionService;

@Service
public class VoteServiceImpl implements IVoteService {

    @Autowired
    private IScheduleService scheduleService;

    @Autowired
    private IVotingSessionService votingSessionService;

    @Autowired
    private IVoteRepository voteRepository;

    @Override
    public void vote(Long scheduleId, Long sessionId, Vote vote) {
        findScheduleById(scheduleId);

        Optional<VotingSession> votingSession = findVotingSessionById(sessionId);

        isVotingSessionExpired(votingSession);

        // TODO verify if member's cpf is able to vote
        // TODO verify if member has already voted

        voteRepository.save(votingSession.get().getId(), vote);
    }

    private Optional<VotingSession> findVotingSessionById(Long sessionId) {
        return Optional.of(votingSessionService.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Voting Session not found")));
    }

    private void findScheduleById(Long scheduleId) {
        @SuppressWarnings("unused")
        Optional<Schedule> schedule = Optional.of(scheduleService.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found")));
    }

    private void isVotingSessionExpired(Optional<VotingSession> votingSession) {
        votingSession.stream()
                .filter(value -> value.getEndTime().isBefore(OffsetDateTime.now()))
                .findAny()
                .ifPresent(value -> {
                    throw new ResourceExpiredException("Voting Session expired.");
                });
    }
}
