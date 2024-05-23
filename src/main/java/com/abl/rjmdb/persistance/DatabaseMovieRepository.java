package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.RentalsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.abl.rjmdb.model.jooq.Tables.RENTALS;

@Repository
@RequiredArgsConstructor
public class DatabaseMovieRepository implements MovieRepository {

    private final DSLContext dsl;

    @Override
    public Mono<RentalsRecord> save(RentalsRecord record) {
        return Flux.from(dsl.insertInto(RENTALS)
                .columns(RENTALS.USER_ID, RENTALS.MOVIE_NAME, RENTALS.START_TIME)
                .values(record.getUserId(), record.getMovieName(), record.getStartTime()))
                .doOnNext(id -> record.setId((long)id))
                .map(ignored -> record)
                .single();
    }

    @Override
    public Mono<RentalsRecord> update(RentalsRecord record) {
        return Flux.from(dsl.update(RENTALS)
                        .set(RENTALS.END_TIME, record.getEndTime()))
                .map(ignored -> record)
                .single();
    }


}
