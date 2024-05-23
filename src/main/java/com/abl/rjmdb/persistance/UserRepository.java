package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<UsersRecord> save(UsersRecord record);

    Mono<UsersRecord> update(UsersRecord record);

}
