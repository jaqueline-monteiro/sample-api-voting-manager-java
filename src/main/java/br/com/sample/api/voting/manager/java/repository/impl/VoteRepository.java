package br.com.sample.api.voting.manager.java.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import br.com.sample.api.voting.manager.java.exceptions.ApiException;
import br.com.sample.api.voting.manager.java.model.Vote;
import br.com.sample.api.voting.manager.java.repository.IVoteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class VoteRepository implements IVoteRepository {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    private static final String INSERT = "INSERT INTO VOTING_SYSTEM.VOTE (ID,SESSION_ID,MEMBER_ID,VOTE) "
            + "VALUES (VOTING_SYSTEM.VOTE_SEQ.NEXTVAL,:sessionId,VOTING_SYSTEM.MEMBER_ID_SEQ.NEXTVAL,:vote)";

    @Override
    public void save(Long sessionId, Vote vote) {
        try {
            log.info("Saving Vote to schedule with sessionId: {}", sessionId);

            var parameters = new MapSqlParameterSource();

            parameters.addValue("sessionId", sessionId);
            parameters.addValue("vote", vote.getMemberVote());

            jdbcOperations.update(INSERT, parameters);

            log.info("Done!");
        } catch (Exception exception) {
            log.error("Failed saving! {}", exception.getMessage());

            throw new ApiException(exception, "Failed to persist Vote to schedule with sessionId: %s", sessionId);
        }
    }
}
