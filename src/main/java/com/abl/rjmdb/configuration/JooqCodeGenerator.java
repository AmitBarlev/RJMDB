package com.abl.rjmdb.configuration;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JooqCodeGenerator implements CommandLineRunner {

    private final JooqCodeGeneratorParameters parameters;
    private final Jdbc jdbc;

    @Override
    @SneakyThrows
    public void run(String... args) {
        Configuration configuration = new Configuration()
                .withJdbc(jdbc)
                .withGenerator(new Generator()
                        .withDatabase(new Database()
                                .withIncludes(parameters.getIncludes())
                                .withExcludes(parameters.getExcludes())
                                .withInputSchema(parameters.getInputSchema()))
                        .withTarget(new Target()
                                .withPackageName(parameters.getPackageName())
                                .withDirectory(parameters.getTargetDirectory())));

        GenerationTool.generate(configuration);
    }
}
