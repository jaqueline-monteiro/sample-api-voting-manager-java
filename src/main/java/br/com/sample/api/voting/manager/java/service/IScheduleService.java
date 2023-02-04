package br.com.sample.api.voting.manager.java.service;

import java.util.Optional;

import br.com.sample.api.voting.manager.java.model.Schedule;

public interface IScheduleService {
    
    Schedule createSchedule(Schedule schedule);
    
    Optional<Schedule> findById(Long id);
}
