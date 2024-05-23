package com.abl.rjmdb.service;

import com.abl.rjmdb.model.RentalRequest;
import com.abl.rjmdb.model.RentalStatus;
import com.abl.rjmdb.model.TerminationStatus;
import com.abl.rjmdb.model.TerminationRequest;
import com.abl.rjmdb.model.jooq.tables.records.RentalsRecord;
import com.abl.rjmdb.persistance.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final ModelMapper mapper;
    private final MovieRepository repository;


    public Mono<RentalStatus> rent(RentalRequest info) {
        return Mono.fromCallable(() -> mapper.map(info, RentalsRecord.class))
                .doOnNext(record -> record.setStartTime(LocalDateTime.now()))
                .doOnNext(repository::save)
                .map(record -> mapper.map(record, RentalStatus.class))
                .doOnError(x -> log.error("Movie rental failed"));
    }

    public Mono<TerminationStatus> terminate(TerminationRequest info) {
        return Mono.fromCallable(() -> mapper.map(info, RentalsRecord.class))
                .doOnNext(record -> record.setEndTime(LocalDateTime.now()))
                .doOnNext(repository::update)
                .map(record -> mapper.map(record, TerminationStatus.class))
                .doOnError(x -> log.error("Cannot return movie"));
    }
}
