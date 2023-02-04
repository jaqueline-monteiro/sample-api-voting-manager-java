package br.com.sample.api.voting.manager.java.service;

import br.com.sample.api.voting.manager.java.model.VotingSession;

public interface IVotingSessionService {
    
    VotingSession openVotingSession(Long id, long duration);
}
