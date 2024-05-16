package com.abl.rjmdb.service;

import com.abl.rjmdb.model.RentalRequest;
import com.abl.rjmdb.model.RentalStatus;
import com.abl.rjmdb.model.TerminationStatus;
import com.abl.rjmdb.model.TerminationRequest;
import com.abl.rjmdb.model.jooq.tables.records.RentalsRecord;
import com.abl.rjmdb.persistance.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final ModelMapper mapper;
    private final MovieRepository repository;


    public Mono<RentalStatus> rent(RentalRequest info) {
        return Mono
                .just(mapper.map(info, RentalsRecord.class))
                .map(repository::save)
                .map(record -> mapper.map(record, RentalStatus.class));
    }

    public Mono<TerminationStatus> terminate(TerminationRequest info) {
        return Mono
                .just(mapper.map(info, RentalsRecord.class))
                .map(repository::save)
                .map(record -> mapper.map(record, TerminationStatus.class));
    }
}
