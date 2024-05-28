package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.RentalRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static com.abl.rjmdb.model.jooq.Tables.RENTAL;

@Repository
@RequiredArgsConstructor
public class MovieDatabaseRepository implements MovieRepository {

    private final DSLContext dsl;

    @Override
    public Mono<RentalRecord> rent(RentalRecord record) {
        return Mono.from(dsl.insertInto(RENTAL)
                .columns(RENTAL.USER_ID, RENTAL.MOVIE_NAME, RENTAL.START_TIME)
                .values(record.getUserId(), record.getMovieName(), record.getStartTime())
                .returning());
    }

    @Override
    public Mono<RentalRecord> terminate(RentalRecord record) {
        return Mono.from(dsl.update(RENTAL)
                        .set(RENTAL.END_TIME, record.getEndTime())
                        .where(RENTAL.ID.eq(record.getId())
                                .and(RENTAL.USER_ID.eq(record.getUserId())))
                        .returning());
    }


}
