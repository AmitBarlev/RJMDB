package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;


@Repository
@Profile("local")
@RequiredArgsConstructor
public class DatabaseUserRepository implements UserRepository {

    private final DSLContext context;

    @Override
    public UsersRecord save(UsersRecord record) {
        Mono.from(x -> record.store())
                .subscribe();

        return record;
    }

    @Override
    public UsersRecord update(UsersRecord record) {
        Mono.from(x -> record.update())
                .subscribe();

        return record;
    }
}
