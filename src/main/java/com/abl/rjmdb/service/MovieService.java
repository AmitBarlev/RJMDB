package com.abl.rjmdb.service;

import com.abl.rjmdb.model.RentalRequest;
import com.abl.rjmdb.model.RentalStatus;
import com.abl.rjmdb.model.TerminationStatus;
import com.abl.rjmdb.model.TerminationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieService {

    public Mono<RentalStatus> rent(RentalRequest info) {
        throw new UnsupportedOperationException();
    }

    public Mono<TerminationStatus> terminate(TerminationRequest info) {
        throw new UnsupportedOperationException();
    }
}
