package com.abl.persistance;

import com.abl.rjmdb.model.jooq.Tables;
import com.abl.rjmdb.model.jooq.tables.records.RentalRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MovieDatabaseRepository implements MovieRepository {

    private final DSLContext dsl;

    @Override
    public Mono<RentalRecord> rent(RentalRecord record) {
        return Mono.from(dsl.insertInto(Tables.RENTAL)
                .columns(Tables.RENTAL.USER_ID, Tables.RENTAL.MOVIE_NAME, Tables.RENTAL.START_TIME)
                .values(record.getUserId(), record.getMovieName(), record.getStartTime())
                .returning());
    }

    @Override
    public Mono<RentalRecord> terminate(RentalRecord record) {
        return Mono.from(dsl.update(Tables.RENTAL)
                        .set(Tables.RENTAL.END_TIME, record.getEndTime())
                        .where(Tables.RENTAL.ID.eq(record.getId())
                                .and(Tables.RENTAL.USER_ID.eq(record.getUserId())))
                        .returning());
    }


}
