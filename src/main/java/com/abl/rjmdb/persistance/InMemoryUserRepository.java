package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("in-memory")
@RequiredArgsConstructor
public class InMemoryUserRepository implements UserRepository {

    private final List<UsersRecord> database;

    @Override
    public UsersRecord save(UsersRecord record) {
        long id = database.size();
        record.setId(id);
        database.add(record);
        return record;
    }

    @Override
    public UsersRecord update(UsersRecord record) {
        return null;
    }
}
