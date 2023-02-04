package br.com.sample.api.voting.manager.java.service;

import java.util.Optional;

import br.com.sample.api.voting.manager.java.model.VotingSession;

public interface IVotingSessionService {
    
    VotingSession openVotingSession(Long id, long duration);
    
    Optional<VotingSession> findById(Long id);
}
