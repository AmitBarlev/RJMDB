package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
@Profile("local")
@RequiredArgsConstructor
public class DatabaseUserRepository implements UserRepository {

    @Override
    public UsersRecord save(UsersRecord record) {
        Mono.fromCallable(record::store)
                .subscribe();

        return record;
    }

    @Override
    public UsersRecord update(UsersRecord record) {
        Mono.fromCallable(record::update)
                .subscribe();

        return record;
    }
}
