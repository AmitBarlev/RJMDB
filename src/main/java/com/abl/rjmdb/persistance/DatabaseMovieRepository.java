package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.RentalRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.abl.rjmdb.model.jooq.Tables.RENTAL;

@Repository
@RequiredArgsConstructor
public class DatabaseMovieRepository implements MovieRepository {

    private final DSLContext dsl;

    @Override
    public Mono<RentalRecord> save(RentalRecord record) {
        return Flux.from(dsl.insertInto(RENTAL)
                .columns(RENTAL.USER_ID, RENTAL.MOVIE_NAME, RENTAL.START_TIME)
                .values(record.getUserId(), record.getMovieName(), record.getStartTime()))
                .doOnNext(id -> record.setId((long)id))
                .map(ignored -> record)
                .single();
    }

    @Override
    public Mono<RentalRecord> update(RentalRecord record) {
        return Flux.from(dsl.update(RENTAL)
                        .set(RENTAL.END_TIME, record.getEndTime()))
                .map(ignored -> record)
                .single();
    }


}
