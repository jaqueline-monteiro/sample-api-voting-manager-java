package br.com.sample.api.voting.manager.java.repository.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.sample.api.voting.manager.java.exceptions.ApiException;
import br.com.sample.api.voting.manager.java.model.VotingSession;
import br.com.sample.api.voting.manager.java.repository.IVotingSessionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class VotingSessionRepository implements IVotingSessionRepository {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    private static final String INSERT = "INSERT INTO VOTING_SYSTEM.VOTING_SESSION (ID,SCHEDULE_ID,START_TIME,END_TIME,USER_INCL,HOR_INCL) "
            + "VALUES (VOTING_SYSTEM.VOTING_SESSION_SEQ.NEXTVAL,:scheduleId,:startTime,:endTime,'Processo API',SYSTIMESTAMP)";

    private static final String FIND_BY_ID = "SELECT ID,SCHEDULE_ID,START_TIME,END_TIME FROM VOTING_SYSTEM.VOTING_SESSION WHERE ID = :id";

    @Override
    public VotingSession save(Long scheduleId, VotingSession votingSession) {
        try {
            log.info("Saving a Voting Session with scheduleId {}", scheduleId);
            
            KeyHolder keyHolder = new GeneratedKeyHolder();

            var parameters = new MapSqlParameterSource();

            parameters.addValue("scheduleId", scheduleId);
            parameters.addValue("startTime", votingSession.getStartTime());
            parameters.addValue("endTime", votingSession.getEndTime());

            jdbcOperations.update(INSERT, parameters, keyHolder);

            log.info("Done!");
            
            long key = Optional.ofNullable(keyHolder)
                    .map(k -> k.getKey().longValue())
                    .orElseThrow(() -> new NullPointerException("KeyHolder is null"));

            votingSession.setId(key);

            return votingSession;
        } catch (Exception exception) {
            log.error("Failed saving! {}", exception.getMessage());
            
            throw new ApiException(exception, "Failed to persist a VotingSession with scheduleId: %s", scheduleId);
        }
    }

    @Override
    public Optional<VotingSession> findById(Long id) {
        try {
            log.info("Finding Voting Session by id {}", id);

            var parameters = new MapSqlParameterSource();

            parameters.addValue("id", id);

            VotingSession result = jdbcOperations.queryForObject(FIND_BY_ID, parameters,
                    new BeanPropertyRowMapper<>(VotingSession.class));

            log.info("Done!");
            log.trace("Result is {}", result);

            return Optional.of(result);
        } catch (EmptyResultDataAccessException exception) {
            log.error("Failed finding! {}", exception.getMessage());

            return Optional.empty();
        }
    }
}
