package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;

public interface UserRepository {

    UsersRecord save(UsersRecord record);

    UsersRecord update(UsersRecord record);

}
