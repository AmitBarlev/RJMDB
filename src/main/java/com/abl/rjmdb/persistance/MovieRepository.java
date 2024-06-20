package com.abl.rjmdb.persistance;


import com.abl.rjmdb.model.jooq.tables.records.RentalRecord;
import reactor.core.publisher.Mono;

public interface MovieRepository {

    Mono<RentalRecord> rent(RentalRecord record);

    Mono<RentalRecord> terminate(RentalRecord record);
}
