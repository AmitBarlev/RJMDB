package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.RentalsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static com.abl.rjmdb.model.jooq.Tables.RENTALS;

@Repository
@RequiredArgsConstructor
public class DatabaseMovieRepository implements MovieRepository {

    private final DSLContext dsl;

    @Override
    public RentalsRecord save(RentalsRecord record) {
        Mono.fromCallable(record::store)
                .subscribe();

        return record;
    }

    @Override
    public RentalsRecord update(RentalsRecord record) {
        Mono.fromFuture(dsl
                        .update(RENTALS)
                        .set(RENTALS.END_TIME, record.getEndTime())
                        .executeAsync()
                        .toCompletableFuture())
                .subscribe();

        return record;
    }


}
