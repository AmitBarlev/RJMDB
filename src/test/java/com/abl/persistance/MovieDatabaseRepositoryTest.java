package com.abl.persistance;

import com.abl.persistance.mock.MovieDatabaseRepositoryMockDataProvider;
import com.abl.rjmdb.model.jooq.tables.records.RentalRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class MovieDatabaseRepositoryTest {

    private MovieDatabaseRepository repository;

    @BeforeEach
    void setUp() {
        MockDataProvider provider = new MovieDatabaseRepositoryMockDataProvider();
        MockConnection connection = new MockConnection(provider);
        DSLContext dsl = DSL.using(connection);
        repository = new MovieDatabaseRepository(dsl);
    }

    @Test
    public void save_sanity_recordSavedMethodUsed() {
        RentalRecord record = mock(RentalRecord.class);

        Mono<RentalRecord> output = repository.rent(record);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void update_sanity_recordUpdateMethodUsed() {
        RentalRecord record = mock(RentalRecord.class);

        doReturn(LocalDateTime.MIN).when(record).getEndTime();

        Mono<RentalRecord> output = repository.terminate(record);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(record).getEndTime();
    }
}
