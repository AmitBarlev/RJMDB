package com.abl.rjmdb.configuration;

import lombok.Data;
import lombok.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Generated
@Configuration
@ConfigurationProperties(prefix = "persistence.database")
public class PostgresParameters {

    private String protocol;
    private String driver;
    private String host;
    private Integer port;
    private String user;
    private String password;
    private String databaseName;
}
