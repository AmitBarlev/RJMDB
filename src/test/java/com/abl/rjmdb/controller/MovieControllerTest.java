package com.abl.rjmdb.controller;

import com.abl.rjmdb.controller.MovieController;
import com.abl.rjmdb.model.RentalRequest;
import com.abl.rjmdb.model.RentalStatus;
import com.abl.rjmdb.model.TerminationRequest;
import com.abl.rjmdb.model.TerminationStatus;
import com.abl.rjmdb.service.MovieService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

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
    public void rent_sanity_monoWithRentalStatus() {
        RentalRequest request = new RentalRequest();
        RentalStatus status = new RentalStatus();

        doReturn(Mono.just(status)).when(movieService).rent(request);

        Mono<RentalStatus> output = movieController.rent(request);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(movieService).rent(request);
    }

    @Test
    public void terminate_sanity_monoWithTerminationStatus() {
        TerminationRequest request = new TerminationRequest();
        TerminationStatus status = new TerminationStatus();

        doReturn(Mono.just(status)).when(movieService).terminate(request);

        Mono<TerminationStatus> output = movieController.terminate(request);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(movieService).terminate(request);
    }
}
