package br.com.sample.api.voting.manager.java.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sample.api.voting.manager.java.model.ErrorDTO;
import br.com.sample.api.voting.manager.java.model.Schedule;
import br.com.sample.api.voting.manager.java.model.Vote;
import br.com.sample.api.voting.manager.java.model.VotingSession;
import br.com.sample.api.voting.manager.java.service.IScheduleService;
import br.com.sample.api.voting.manager.java.service.IVoteService;
import br.com.sample.api.voting.manager.java.service.IVotingSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1")
@Api(value = "ScheduleController", tags = "ScheduleController", consumes = "application/json", produces = "application/json")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;
    
    @Autowired
    private IVotingSessionService votingSessionService;
    
    @Autowired
    private IVoteService voteService;

    @ApiOperation(value = "Endpoint to register a new schedule")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created", response = ErrorDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDTO.class)
    })
    @PostMapping("/schedules")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Schedule> createSchedule(@RequestBody @Valid Schedule schedule) {        
        Schedule created = scheduleService.createSchedule(schedule);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @ApiOperation(value = "Endpoint to open a voting session in a schedule")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created", response = ErrorDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDTO.class)
    })
    @PostMapping("/schedules/{id}/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VotingSession> openVotingSession(
            @ApiParam(value = "Schedule identification number", required = true) @PathVariable Long id,
            @ApiParam(value = "Duration of the voting session in minutes", required = false, defaultValue = "1") @RequestParam(required = false) long duration) { 
        VotingSession created = votingSessionService.openVotingSession(id, duration);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/schedules/{id}/voting-session")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }
    
    @ApiOperation(value = "Endpoint to receive votes from members")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = ErrorDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDTO.class)
    })
    @PostMapping("schedules/{scheduleId}/sessions/{sessionId}/votes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> registerVote(
            @ApiParam(value = "Schedule identification number", required = true) @PathVariable Long scheduleId,
            @ApiParam(value = "Voting Session identification number", required = true) @PathVariable Long sessionId,
            @RequestBody(required = true) @Valid Vote vote) {
        
        voteService.vote(scheduleId, sessionId, vote);

        return new ResponseEntity<>("Vote registered successfully!", HttpStatus.OK);
    }
}
