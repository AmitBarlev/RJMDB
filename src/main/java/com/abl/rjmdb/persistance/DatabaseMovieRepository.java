package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.RentalsRecord;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class DatabaseMovieRepository implements MovieRepository {

    @Override
    public RentalsRecord save(RentalsRecord record) {
        Mono.from(x -> record.store())
                .subscribe();

        return record;
    }
}
