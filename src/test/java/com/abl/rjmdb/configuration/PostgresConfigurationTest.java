package com.abl.rjmdb.configuration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PostgresConfigurationTest {

    @InjectMocks
    private PostgresConfiguration configuration;

    @Mock
    private PostgresParameters parameters;

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
    public void dbContext_sanity_notNullAndParametersVerified() {
        doReturn("postgresql").when(parameters).getDriver();
        doReturn("b").when(parameters).getHost();
        doReturn("c").when(parameters).getProtocol();
        doReturn("d").when(parameters).getUser();
        doReturn("e").when(parameters).getPassword();
        doReturn("f").when(parameters).getDatabaseName();
        doReturn(1).when(parameters).getPort();

        assertNotNull(configuration.dbContext());

        verify(parameters).getDriver();
        verify(parameters).getHost();
        verify(parameters).getProtocol();
        verify(parameters).getUser();
        verify(parameters).getPassword();
        verify(parameters).getDatabaseName();
        verify(parameters).getPort();
    }

    @Test
    public void dbContext_exceptionThorwn_notNullAndParametersVerified() {
        doThrow(new RuntimeException()).when(parameters).getDriver();

        assertThrows(RuntimeException.class, () -> configuration.dbContext());

        verify(parameters).getDriver();
    }
}
