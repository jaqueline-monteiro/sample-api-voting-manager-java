package br.com.sample.api.voting.manager.java.repository;

import java.util.Optional;

import br.com.sample.api.voting.manager.java.model.Schedule;

public interface IScheduleRepository {
    Schedule save(Schedule schedule);

    Optional<Schedule> findById(Long id);
}
