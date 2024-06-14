package com.abl.persistance;

import com.abl.rjmdb.model.jooq.Tables;
import com.abl.rjmdb.model.jooq.tables.records.ClientRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
@RequiredArgsConstructor
public class UserDatabaseRepository implements UserRepository {

    private final DSLContext dsl;

    @Override
    public Mono<ClientRecord> save(ClientRecord record) {
        return Mono.from(dsl.insertInto(Tables.CLIENT)
                        .columns(Tables.CLIENT.FIRST_NAME, Tables.CLIENT.LAST_NAME, Tables.CLIENT.ADDRESS)
                        .values(record.getFirstName(), record.getLastName(), record.getAddress())
                        .returning());
    }

    @Override
    public Mono<ClientRecord> update(ClientRecord record) {
        return Mono.from(dsl.update(Tables.CLIENT)
                        .set(Tables.CLIENT.FIRST_NAME, record.getFirstName())
                        .set(Tables.CLIENT.LAST_NAME, record.getLastName())
                        .set(Tables.CLIENT.ADDRESS, record.getAddress())
                        .where(Tables.CLIENT.ID.eq(record.getId()))
                        .returning());
    }
}
