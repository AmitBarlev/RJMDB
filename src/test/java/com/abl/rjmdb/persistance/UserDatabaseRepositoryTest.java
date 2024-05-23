package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserDatabaseRepositoryTest {

    private final DatabaseUserRepository repository = new DatabaseUserRepository();


    @Test
    public void save_sanity_recordHasBeenStored() {
        UsersRecord record = mock(UsersRecord.class);

        record = repository.save(record);

        verify(record).store();
    }

    @Test
    public void update_sanity_recordHasBeenStored() {
        UsersRecord record = mock(UsersRecord.class);

        record = repository.update(record);

        verify(record).update();
    }
}
