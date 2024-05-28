package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.ClientRecord;
import com.abl.rjmdb.persistance.mock.UserDatabaseRepositoryMockDataProvider;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class UserDatabaseRepositoryTest {

    private UserDatabaseRepository repository;

    @BeforeEach
    void setUp() {
        MockDataProvider provider = new UserDatabaseRepositoryMockDataProvider();
        MockConnection connection = new MockConnection(provider);
        DSLContext dsl = DSL.using(connection);
        repository = new UserDatabaseRepository(dsl);
    }

    @Test
    public void save_sanity_recordHasBeenStored() {
        ClientRecord record = mock(ClientRecord.class);

        Mono<ClientRecord> output = repository.save(record);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void update_sanity_recordHasBeenStored() {
        ClientRecord record = mock(ClientRecord.class);

        doReturn(10L).when(record).getId();

        Mono<ClientRecord> output = repository.update(record);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(record).getId();
    }
}
