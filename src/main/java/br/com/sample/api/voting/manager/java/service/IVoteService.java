package br.com.sample.api.voting.manager.java.service;

import br.com.sample.api.voting.manager.java.model.Vote;

public interface IVoteService {

    void vote(Long scheduleId, Long votingId, Vote vote);
}
