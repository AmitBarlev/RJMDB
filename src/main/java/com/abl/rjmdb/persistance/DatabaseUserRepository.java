package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.abl.rjmdb.model.jooq.Tables.USERS;


@Repository
@RequiredArgsConstructor
public class DatabaseUserRepository implements UserRepository {

    private final DSLContext dsl;

    @Override
    public Mono<UsersRecord> save(UsersRecord record) {
        return Flux.from(dsl.insertInto(USERS)
                        .columns(USERS.FIRST_NAME, USERS.LAST_NAME, USERS.ADDRESS)
                        .values(record.getFirstName(), record.getLastName(), record.getAddress()))
                .doOnNext(id -> record.setId((long) id))
                .map(ignored -> record)
                .single();
    }

    @Override
    public Mono<UsersRecord> update(UsersRecord record) {
        return Flux.from(dsl.update(USERS)
                        .set(USERS.FIRST_NAME, record.getFirstName())
                        .set(USERS.LAST_NAME, record.getLastName())
                        .set(USERS.ADDRESS, record.getAddress())
                        .where(USERS.ID.eq(record.getId())))
                .map(ignored -> record)
                .single();
    }
}
