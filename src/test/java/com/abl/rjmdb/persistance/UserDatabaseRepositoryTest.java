package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.ClientRecord;
import com.abl.rjmdb.persistance.mock.DoNothingDataProvider;
import lombok.SneakyThrows;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class UserDatabaseRepositoryTest {

    private DatabaseUserRepository repository;

    @BeforeEach
    void setUp() {
        MockDataProvider provider = new DoNothingDataProvider();
        MockConnection connection = new MockConnection(provider);
        DSLContext dsl = DSL.using(connection);
        repository = new DatabaseUserRepository(dsl);
    }

    @Test
    public void save_sanity_recordHasBeenStored() {
        ClientRecord record = mock(ClientRecord.class);

        Mono<ClientRecord> output = repository.save(record);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(record).setId(-1L);
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
