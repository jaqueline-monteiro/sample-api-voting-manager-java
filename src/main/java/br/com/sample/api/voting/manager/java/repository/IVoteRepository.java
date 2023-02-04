package br.com.sample.api.voting.manager.java.repository;

import br.com.sample.api.voting.manager.java.model.Vote;

public interface IVoteRepository {

    void save(Long sessionId, Vote vote);
}
