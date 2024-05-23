package com.abl.rjmdb.persistance;

import com.abl.rjmdb.model.jooq.tables.records.RentalsRecord;
import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;
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
        RentalsRecord record = mock(RentalsRecord.class);

        Mono<RentalsRecord> output = repository.save(record);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(record).setId(-1L);
    }

    @Test
    public void update_sanity_recordUpdateMethodUsed() {
        RentalsRecord record = mock(RentalsRecord.class);

        doReturn(LocalDateTime.MIN).when(record).getEndTime();

        Mono<RentalsRecord> output = repository.update(record);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(record).getEndTime();
    }
}
