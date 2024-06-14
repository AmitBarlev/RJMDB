package com.abl.service;

import com.abl.model.RentalRequest;
import com.abl.model.RentalStatus;
import com.abl.model.TerminationStatus;
import com.abl.model.TerminationRequest;
import com.abl.persistance.MovieRepository;
import com.abl.rjmdb.model.jooq.tables.records.RentalRecord;
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
        RentalRecord record = mapper.map(info, RentalRecord.class);
        record.setStartTime(LocalDateTime.now());
        return repository.rent(record)
                .map(savedRecord -> mapper.map(savedRecord, RentalStatus.class))
                .doOnError(error -> log.error("Movie rental failed", error));
    }

    public Mono<TerminationStatus> terminate(TerminationRequest info) {
        RentalRecord record = mapper.map(info, RentalRecord.class);
        record.setEndTime(LocalDateTime.now());
        return repository.terminate(record)
                .map(savedRecord -> mapper.map(savedRecord, TerminationStatus.class))
                .doOnError(x -> log.error("Cannot return movie"));
    }
}
