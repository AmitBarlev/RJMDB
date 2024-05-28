package com.abl.rjmdb.service;

import com.abl.rjmdb.model.RentalRequest;
import com.abl.rjmdb.model.RentalStatus;
import com.abl.rjmdb.model.TerminationRequest;
import com.abl.rjmdb.model.TerminationStatus;
import com.abl.rjmdb.model.jooq.tables.records.RentalRecord;
import com.abl.rjmdb.persistance.MovieRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private ModelMapper mapper;
    @Mock
    private MovieRepository movieRepository;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    @SneakyThrows
    void tearDown() {
        closeable.close();
    }

    @Test
    public void rent_sanity_savedSuccessfully() {
        long id = 1L;
        String a = "a";
        RentalRequest rentalRequest = new RentalRequest(id, a);
        RentalRecord record = new RentalRecord(id, id, a, LocalDateTime.MIN, LocalDateTime.MAX);
        RentalStatus status = new RentalStatus(id, a, LocalDateTime.MIN);

        doReturn(record).when(mapper).map(rentalRequest, RentalRecord.class);
        doReturn(Mono.just(record)).when(movieRepository).rent(record);
        doReturn(status).when(mapper).map(record, RentalStatus.class);

        Mono<RentalStatus> output = movieService.rent(rentalRequest);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(mapper).map(rentalRequest, RentalRecord.class);
        verify(movieRepository).rent(record);
        verify(mapper).map(record, RentalStatus.class);
    }

    @Test
    public void rent_mappingToRecordFailed_monoExpectsError() {
        long id = 1L;
        String a = "a";
        RentalRequest rentalRequest = new RentalRequest(id, a);

        doThrow(new RuntimeException()).when(mapper).map(rentalRequest, RentalRecord.class);

        assertThrows(RuntimeException.class, () -> movieService.rent(rentalRequest));

        verify(mapper).map(rentalRequest, RentalRecord.class);
    }

    @Test
    public void rent_savingToDbFailed_monoExpectsError() {
        long id = 1L;
        String a = "a";
        RentalRequest rentalRequest = new RentalRequest(id, a);
        RentalRecord record = new RentalRecord(id, id, a, LocalDateTime.MIN, LocalDateTime.MAX);

        doReturn(record).when(mapper).map(rentalRequest, RentalRecord.class);
        doThrow(new RuntimeException()).when(movieRepository).rent(record);

        assertThrows(RuntimeException.class, () -> movieService.rent(rentalRequest));

        verify(mapper).map(rentalRequest, RentalRecord.class);
        verify(movieRepository).rent(record);
    }

    @Test
    public void rent_mappingToRentalStatusFailed_monoExpectsError() {
        long id = 1L;
        String a = "a";
        RentalRequest rentalRequest = new RentalRequest(id, a);
        RentalRecord record = new RentalRecord(id, id, a, LocalDateTime.MIN, LocalDateTime.MAX);

        doReturn(record).when(mapper).map(rentalRequest, RentalRecord.class);
        doReturn(Mono.just(record)).when(movieRepository).rent(record);
        doThrow(new RuntimeException()).when(mapper).map(record, RentalStatus.class);

        Mono<RentalStatus> output = movieService.rent(rentalRequest);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(rentalRequest, RentalRecord.class);
        verify(movieRepository).rent(record);
        verify(mapper).map(record, RentalStatus.class);
    }

    @Test
    public void terminate_sanity_savedSuccessfully() {
        long id = 1L;
        long userId = 2L;
        String a = "a";
        TerminationRequest terminationRequest = new TerminationRequest(id, userId);
        RentalRecord record = new RentalRecord(id, userId, a, LocalDateTime.MIN, LocalDateTime.MAX);
        TerminationStatus status = new TerminationStatus();

        doReturn(record).when(mapper).map(terminationRequest, RentalRecord.class);
        doReturn(Mono.just(record)).when(movieRepository).terminate(record);
        doReturn(status).when(mapper).map(record, TerminationStatus.class);

        Mono<TerminationStatus> output = movieService.terminate(terminationRequest);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(mapper).map(terminationRequest, RentalRecord.class);
        verify(movieRepository).terminate(record);
        verify(mapper).map(record, TerminationStatus.class);
    }

    @Test
    public void terminate_mappingToRecordFailed_monoExpectsError() {
        long id = 1L;
        long userId = 2L;

        TerminationRequest terminationRequest = new TerminationRequest(id, userId);

        doThrow(new RuntimeException()).when(mapper).map(terminationRequest, RentalRecord.class);

        assertThrows(RuntimeException.class, () -> movieService.terminate(terminationRequest));

        verify(mapper).map(terminationRequest, RentalRecord.class);
    }

    @Test
    public void terminate_updateToDbFailed_monoExpectsError() {
        long id = 1L;
        long userId = 2L;
        String a = "a";
        TerminationRequest terminationRequest = new TerminationRequest(id, userId);
        RentalRecord record = new RentalRecord(id, id, a, LocalDateTime.MIN, LocalDateTime.MAX);

        doReturn(record).when(mapper).map(terminationRequest, RentalRecord.class);
        doThrow(new RuntimeException()).when(movieRepository).terminate(record);

        assertThrows(RuntimeException.class, () -> movieService.terminate(terminationRequest));

        verify(mapper).map(terminationRequest, RentalRecord.class);
        verify(movieRepository).terminate(record);
    }

    @Test
    public void terminate_mappingToTerminationStatusFailed_monoExpectsError() {
        long id = 1L;
        long userId = 2L;
        String a = "a";
        TerminationRequest terminationRequest = new TerminationRequest(id, userId);
        RentalRecord record = new RentalRecord(id, id, a, LocalDateTime.MIN, LocalDateTime.MAX);

        doReturn(record).when(mapper).map(terminationRequest, RentalRecord.class);
        doReturn(Mono.just(record)).when(movieRepository).terminate(record);
        doThrow(new RuntimeException()).when(mapper).map(record, TerminationStatus.class);

        Mono<TerminationStatus> output = movieService.terminate(terminationRequest);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(terminationRequest, RentalRecord.class);
        verify(movieRepository).terminate(record);
        verify(mapper).map(record, TerminationStatus.class);
    }
}
