package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.ClientRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.abl.rjmdb.model.jooq.Tables.CLIENT;


@Repository
@RequiredArgsConstructor
public class DatabaseUserRepository implements UserRepository {

    private final DSLContext dsl;

    @Override
    public Mono<ClientRecord> save(ClientRecord record) {
        return Flux.from(dsl.insertInto(CLIENT)
                        .columns(CLIENT.FIRST_NAME, CLIENT.LAST_NAME, CLIENT.ADDRESS)
                        .values(record.getFirstName(), record.getLastName(), record.getAddress()))
                .doOnNext(id -> record.setId((long) id))
                .map(ignored -> record)
                .single();
    }

    @Override
    public Mono<ClientRecord> update(ClientRecord record) {
        return Flux.from(dsl.update(CLIENT)
                        .set(CLIENT.FIRST_NAME, record.getFirstName())
                        .set(CLIENT.LAST_NAME, record.getLastName())
                        .set(CLIENT.ADDRESS, record.getAddress())
                        .where(CLIENT.ID.eq(record.getId())))
                .map(ignored -> record)
                .single();
    }
}
