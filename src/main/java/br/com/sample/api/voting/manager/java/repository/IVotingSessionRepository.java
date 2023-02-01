package br.com.sample.api.voting.manager.java.repository;

import java.util.Optional;

import br.com.sample.api.voting.manager.java.model.VotingSession;

public interface IVotingSessionRepository {
    void save(Long scheduleId, VotingSession votingSession);

    Optional<VotingSession> findById(Long id);
}
