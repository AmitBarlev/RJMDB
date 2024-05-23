package com.abl.rjmdb.configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PostgresConfiguration {

    private final PostgresParameters parameters;

    @Bean
    @SneakyThrows
    public DSLContext dbContext() {
        ConnectionFactoryOptions options = ConnectionFactoryOptions
                .builder()
                .option(ConnectionFactoryOptions.DRIVER, parameters.getDriver())
                .option(ConnectionFactoryOptions.PROTOCOL, parameters.getProtocol())
                .option(ConnectionFactoryOptions.HOST, parameters.getHost())
                .option(ConnectionFactoryOptions.PORT, parameters.getPort())
                .option(ConnectionFactoryOptions.USER, parameters.getUser())
                .option(ConnectionFactoryOptions.PASSWORD, parameters.getPassword())
                .option(ConnectionFactoryOptions.DATABASE, parameters.getDatabaseName())
                .build();

        ConnectionFactory connectionFactory = ConnectionFactories.get(options);
        return DSL.using(connectionFactory);
    }
}
