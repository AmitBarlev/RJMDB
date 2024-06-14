package com.abl.controller;

import com.abl.model.RentalRequest;
import com.abl.model.RentalStatus;
import com.abl.model.TerminationStatus;
import com.abl.model.TerminationRequest;
import com.abl.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/movie")
public class MovieController {

    private final MovieService movieService;

    @PostMapping(value = "rent")
    public Mono<RentalStatus> rent(RentalRequest info) {
        return movieService.rent(info);
    }

    @PostMapping(value = "terminate")
    public Mono<TerminationStatus> terminate(TerminationRequest info) {
        return movieService.terminate(info);
    }
}
