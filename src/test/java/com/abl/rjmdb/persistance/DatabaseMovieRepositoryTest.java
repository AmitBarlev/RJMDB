package com.abl.rjmdb.persistance;

import com.abl.rjmdb.persistance.mock.DoNothingDataProvider;
import com.abl.rjmdb.model.jooq.tables.records.RentalsRecord;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DatabaseMovieRepositoryTest {

    private DatabaseMovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        movieRepository = new DatabaseMovieRepository(
                        DSL.using(new MockConnection(new DoNothingDataProvider())));
    }

    @Test
    public void save_sanity_recordSavedMethodUsed() {
        RentalsRecord record = mock(RentalsRecord.class);

        record = movieRepository.save(record);

        assertNotNull(record);

        verify(record).store();
    }

    @Test
    public void update_sanity_recordUpdateMethodUsed() {
        RentalsRecord record = mock(RentalsRecord.class);

        doReturn(LocalDateTime.MIN).when(record).getEndTime();

        record = movieRepository.update(record);

        verify(record).getEndTime();
    }
}
