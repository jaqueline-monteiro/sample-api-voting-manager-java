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
import br.com.sample.api.voting.manager.java.model.Schedule;
import br.com.sample.api.voting.manager.java.repository.IScheduleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ScheduleRepositoryImpl implements IScheduleRepository {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    private static final String INSERT = "INSERT INTO VOTING_SYSTEM.SCHEDULE (ID,TITLE,DESCRIPTION,USER_INCL,HOR_INCL) "
            + "VALUES (VOTING_SYSTEM.SCHEDULE_SEQ.NEXTVAL,:title,:description,'Processo API',SYSTIMESTAMP)";

    private static final String FIND_BY_ID = "SELECT ID,TITLE,DESCRIPTION FROM VOTING_SYSTEM.SCHEDULE WHERE ID = :id";

    @Override
    public Schedule save(Schedule schedule) {
        try {
            log.info("Saving Schedule: {} - {}", schedule.getTitle(), schedule.getDescription());

            KeyHolder keyHolder = new GeneratedKeyHolder();

            var parameters = new MapSqlParameterSource();

            parameters.addValue("title", schedule.getTitle());
            parameters.addValue("description", schedule.getDescription());

            jdbcOperations.update(INSERT, parameters, keyHolder);

            log.info("Done!");

            long key = Optional.ofNullable(keyHolder)
                    .map(k -> k.getKey().longValue())
                    .orElseThrow(() -> new NullPointerException("KeyHolder is null"));

            schedule.setId(key);

            return schedule;
        } catch (Exception exception) {
            log.error("Failed saving! {}", exception.getMessage());

            throw new ApiException(exception, "Failed to persist schedule: %s - %s", schedule.getTitle(), schedule.getDescription());
        }
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        try {
            log.info("Finding Schedule by id {}", id);

            var parameters = new MapSqlParameterSource();

            parameters.addValue("id", id);

            Schedule result = jdbcOperations.queryForObject(FIND_BY_ID, parameters,
                    new BeanPropertyRowMapper<>(Schedule.class));

            log.info("Done!");
            log.trace("Result is {}", result);

            return Optional.of(result);
        } catch (EmptyResultDataAccessException exception) {
            log.error("Failed finding! {}", exception.getMessage());

            return Optional.empty();
        }
    }
}
