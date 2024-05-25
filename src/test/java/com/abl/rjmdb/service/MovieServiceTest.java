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

import static org.mockito.Mockito.*;

public class MovieServiceTest {

    @InjectMocks
    private MovieService userService;

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
        RentalRequest rentalRequest = new RentalRequest(id, a, LocalDateTime.MIN);
        RentalRecord record = new RentalRecord(id, id, a, LocalDateTime.MIN, LocalDateTime.MAX);
        RentalStatus status = new RentalStatus(id, a, LocalDateTime.MIN);

        doReturn(record).when(mapper).map(rentalRequest, RentalRecord.class);
        doReturn(Mono.just(record)).when(movieRepository).save(record);
        doReturn(status).when(mapper).map(record, RentalStatus.class);

        Mono<RentalStatus> output = userService.rent(rentalRequest);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(mapper).map(rentalRequest, RentalRecord.class);
        verify(movieRepository).save(record);
        verify(mapper).map(record, RentalStatus.class);
    }

    @Test
    public void rent_mappingToRecordFailed_monoExpectsError() {
        long id = 1L;
        String a = "a";
        RentalRequest rentalRequest = new RentalRequest(id, a, LocalDateTime.MIN);

        doThrow(new RuntimeException()).when(mapper).map(rentalRequest, RentalRecord.class);

        Mono<RentalStatus> output = userService.rent(rentalRequest);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(rentalRequest, RentalRecord.class);
    }

    @Test
    public void rent_savingToDbFailed_monoExpectsError() {
        long id = 1L;
        String a = "a";
        RentalRequest rentalRequest = new RentalRequest(id, a, LocalDateTime.MIN);
        RentalRecord record = new RentalRecord(id, id, a, LocalDateTime.MIN, LocalDateTime.MAX);

        doReturn(record).when(mapper).map(rentalRequest, RentalRecord.class);
        doThrow(new RuntimeException()).when(movieRepository).save(record);

        Mono<RentalStatus> output = userService.rent(rentalRequest);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(rentalRequest, RentalRecord.class);
        verify(movieRepository).save(record);
    }

    @Test
    public void rent_mappingToRentalStatusFailed_monoExpectsError() {
        long id = 1L;
        String a = "a";
        RentalRequest rentalRequest = new RentalRequest(id, a, LocalDateTime.MIN);
        RentalRecord record = new RentalRecord(id, id, a, LocalDateTime.MIN, LocalDateTime.MAX);

        doReturn(record).when(mapper).map(rentalRequest, RentalRecord.class);
        doReturn(Mono.just(record)).when(movieRepository).save(record);
        doThrow(new RuntimeException()).when(mapper).map(record, RentalStatus.class);

        Mono<RentalStatus> output = userService.rent(rentalRequest);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(rentalRequest, RentalRecord.class);
        verify(movieRepository).save(record);
        verify(mapper).map(record, RentalStatus.class);
    }

    @Test
    public void terminate_sanity_savedSuccessfully() {
        long id = 1L;
        String a = "a";
        TerminationRequest terminationRequest = new TerminationRequest(id, LocalDateTime.MIN);
        RentalRecord record = new RentalRecord(id, id, a, LocalDateTime.MIN, LocalDateTime.MAX);
        TerminationStatus status = new TerminationStatus();

        doReturn(record).when(mapper).map(terminationRequest, RentalRecord.class);
        doReturn(Mono.just(record)).when(movieRepository).update(record);
        doReturn(status).when(mapper).map(record, TerminationStatus.class);

        Mono<TerminationStatus> output = userService.terminate(terminationRequest);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(mapper).map(terminationRequest, RentalRecord.class);
        verify(movieRepository).update(record);
        verify(mapper).map(record, TerminationStatus.class);
    }

    @Test
    public void terminate_mappingToRecordFailed_monoExpectsError() {
        long id = 1L;
        TerminationRequest terminationRequest = new TerminationRequest(id, LocalDateTime.MIN);

        doThrow(new RuntimeException()).when(mapper).map(terminationRequest, RentalRecord.class);

        Mono<TerminationStatus> output = userService.terminate(terminationRequest);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(terminationRequest, RentalRecord.class);
    }

    @Test
    public void terminate_updateToDbFailed_monoExpectsError() {
        long id = 1L;
        String a = "a";
        TerminationRequest terminationRequest = new TerminationRequest(id, LocalDateTime.MIN);
        RentalRecord record = new RentalRecord(id, id, a, LocalDateTime.MIN, LocalDateTime.MAX);

        doReturn(record).when(mapper).map(terminationRequest, RentalRecord.class);
        doThrow(new RuntimeException()).when(movieRepository).update(record);

        Mono<TerminationStatus> output = userService.terminate(terminationRequest);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(terminationRequest, RentalRecord.class);
        verify(movieRepository).update(record);
    }

    @Test
    public void terminate_mappingToTerminationStatusFailed_monoExpectsError() {
        long id = 1L;
        String a = "a";
        TerminationRequest terminationRequest = new TerminationRequest(id, LocalDateTime.MIN);
        RentalRecord record = new RentalRecord(id, id, a, LocalDateTime.MIN, LocalDateTime.MAX);

        doReturn(record).when(mapper).map(terminationRequest, RentalRecord.class);
        doReturn(Mono.just(record)).when(movieRepository).update(record);
        doThrow(new RuntimeException()).when(mapper).map(record, TerminationStatus.class);

        Mono<TerminationStatus> output = userService.terminate(terminationRequest);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(terminationRequest, RentalRecord.class);
        verify(movieRepository).update(record);
        verify(mapper).map(record, TerminationStatus.class);
    }
}
