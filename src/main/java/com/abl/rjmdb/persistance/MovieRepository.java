package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.RentalsRecord;
import reactor.core.publisher.Mono;

public interface MovieRepository {

    Mono<RentalsRecord> save(RentalsRecord record);

    Mono<RentalsRecord> update(RentalsRecord record);
}
