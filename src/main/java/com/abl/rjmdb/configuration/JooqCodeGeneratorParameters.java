package com.abl.rjmdb.configuration;

import lombok.Data;
import lombok.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Generated
@Configuration
@ConfigurationProperties(prefix = "persistence.generator")
public class JooqCodeGeneratorParameters {

    private String databaseName;
    private String includes;
    private String excludes;
    private String inputSchema;
    private String packageName;
    private String targetDirectory;
}
