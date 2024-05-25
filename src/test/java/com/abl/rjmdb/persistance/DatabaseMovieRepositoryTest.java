package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.RentalRecord;
import com.abl.rjmdb.persistance.mock.DoNothingDataProvider;
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

public class DatabaseMovieRepositoryTest {

    private DatabaseMovieRepository repository;

    @BeforeEach
    void setUp() {
        MockDataProvider provider = new DoNothingDataProvider();
        MockConnection connection = new MockConnection(provider);
        DSLContext dsl = DSL.using(connection);
        repository = new DatabaseMovieRepository(dsl);
    }

    @Test
    public void save_sanity_recordSavedMethodUsed() {
        RentalRecord record = mock(RentalRecord.class);

        Mono<RentalRecord> output = repository.save(record);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(record).setId(-1L);
    }

    @Test
    public void update_sanity_recordUpdateMethodUsed() {
        RentalRecord record = mock(RentalRecord.class);

        doReturn(LocalDateTime.MIN).when(record).getEndTime();

        Mono<RentalRecord> output = repository.update(record);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(record).getEndTime();
    }
}
