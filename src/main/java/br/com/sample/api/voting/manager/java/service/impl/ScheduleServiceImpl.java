package br.com.sample.api.voting.manager.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sample.api.voting.manager.java.model.Schedule;
import br.com.sample.api.voting.manager.java.repository.IScheduleRepository;
import br.com.sample.api.voting.manager.java.service.IScheduleService;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    @Autowired
    private IScheduleRepository repository;

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return repository.save(schedule);
    }
}
