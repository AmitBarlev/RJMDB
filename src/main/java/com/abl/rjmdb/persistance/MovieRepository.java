package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.RentalsRecord;
import reactor.core.publisher.Mono;

public interface MovieRepository {

    RentalsRecord save(RentalsRecord record);

    RentalsRecord update(RentalsRecord record);
}
