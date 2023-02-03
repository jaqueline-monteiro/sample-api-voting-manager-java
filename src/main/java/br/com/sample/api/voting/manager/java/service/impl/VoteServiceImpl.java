package br.com.sample.api.voting.manager.java.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sample.api.voting.manager.java.exceptions.ResourceNotFoundException;
import br.com.sample.api.voting.manager.java.model.Schedule;
import br.com.sample.api.voting.manager.java.model.Vote;
import br.com.sample.api.voting.manager.java.model.VotingSession;
import br.com.sample.api.voting.manager.java.repository.IScheduleRepository;
import br.com.sample.api.voting.manager.java.repository.IVoteRepository;
import br.com.sample.api.voting.manager.java.repository.IVotingSessionRepository;
import br.com.sample.api.voting.manager.java.service.IVoteService;

@Service
public class VoteServiceImpl implements IVoteService {
    
    @Autowired
    private IScheduleRepository scheduleRepository;

    @Autowired
    private IVotingSessionRepository votingSessionRepository;

    @Autowired
    private IVoteRepository voteRepository;

    @Override
    public void vote(Long scheduleId, Long sessionId, Vote vote) {
        Optional<Schedule> schedule = Optional
                .of(scheduleRepository.findById(scheduleId)
                        .orElseThrow(() -> new ResourceNotFoundException("Schedule not found")));
        
        Optional<VotingSession> votingSession = Optional
                .of(votingSessionRepository.findById(sessionId)
                        .orElseThrow(() -> new ResourceNotFoundException("Voting Session not found")));

        // TODO verify if CPF is able to vote (external integration)
        // TODO verify if member has already voted
        // TODO configurate a pattern for vote ('Sim'/'Não')

        voteRepository.vote(votingSession.get().getId(), vote);
    }
}
