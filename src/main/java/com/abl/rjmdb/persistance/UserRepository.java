package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.ClientRecord;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<ClientRecord> save(ClientRecord record);

    Mono<ClientRecord> update(ClientRecord record);

}
